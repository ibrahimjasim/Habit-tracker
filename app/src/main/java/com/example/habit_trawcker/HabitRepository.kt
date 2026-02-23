package com.example.habit_trawcker

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.toObject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.tasks.await

class HabitRepository(
    private val dao: HabitDao,
    private val userId: String
) {
    private val collection = FirebaseFirestore.getInstance()
        .collection("users")
        .document(userId)
        .collection("habits")

    val allHabits: Flow<List<Habit>> = dao.getAllFlow()

    suspend fun addHabit(habit: Habit) {
        val insertedId = dao.insert(habit).toInt()
        val habitWithId = habit.copy(id = insertedId)
        collection.document(insertedId.toString()).set(habitWithId).await()
    }

    suspend fun updateHabit(habit: Habit) {
        dao.update(habit)
        collection.document(habit.id.toString()).set(habit).await()
    }

    suspend fun deleteHabit(habit: Habit) {
        dao.delete(habit)
        collection.document(habit.id.toString()).delete().await()
    }

    suspend fun syncFromFirestore() {
        val snapshot = collection.get().await()
        val habits = snapshot.documents.mapNotNull { doc ->
            doc.toObject<Habit>()
        }
        if (habits.isNotEmpty()) {
            dao.clearAll()
            habits.forEach { dao.insert(it) }
        }
    }
}
