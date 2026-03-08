# 📋 Habitify — Habit Tracker App

A mobile habit tracking application built with Kotlin that helps users build and maintain daily habits. Users can register, log in, and manage personal habits with full CRUD functionality.

---


| Sign In | Register | Dashboard |
|---------|----------|-----------|
| Email/password login with "Forgot password?" flow | New account registration | Habit list with add, edit & delete |

---

## ✨ Features

- 🔐 **Authentication** — Sign up, sign in, and log out securely
- ➕ **Add Habits** — Create new habits with a name and a goal description
- ✏️ **Edit Habits** — Update existing habit details at any time
- 🗑️ **Delete Habits** — Remove habits you no longer need
- ✅ **Check-off Habits** — Mark habits as done for the day
- 🎨 **Color-coded cards** — Visual feedback per habit status

---

## 🛠️ Tech Stack

| Layer | Technology |
|-------|-----------|
| Language | Kotlin |
| Platform | Android (Native) |
| Auth | Firebase Authentication |
| Database | Firebase Firestore |
| Backend | Firebase |
| Build Tool | Gradle |

---

## 🚀 Getting Started

### Prerequisites

Make sure you have the following installed:

- [Android Studio](https://developer.android.com/studio) (latest stable)
- Android SDK (API level 26 or higher recommended)
- A [Firebase](https://firebase.google.com/) project with **Authentication** and **Firestore** enabled

---

### Installation

1. **Clone the repository**

```bash
git clone https://github.com/ibrahimjasim/Habit-tracker.git
cd Habit-tracker
```

2. **Add your Firebase configuration**

- Go to your [Firebase Console](https://console.firebase.google.com/)
- Create a new Android app and download the `google-services.json` file
- Place it in the `app/` directory:

```
Habit-tracker/
└── app/
    └── google-services.json   ← place it here
```

3. **Open in Android Studio**

```
File → Open → Select the cloned project folder
```

4. **Sync Gradle**

Android Studio will prompt you to sync — click **Sync Now**

5. **Run the app**

- Connect a physical Android device or start an emulator
- Click the ▶️ **Run** button in Android Studio, or use:

```bash
./gradlew installDebug
```

---

## 📂 Project Structure

```
Habit-tracker/
├── app/
│   ├── google-services.json        # Firebase config (not committed)
│   └── src/
│       └── main/
│           ├── java/com/example/habittracker/
│           │   ├── MainActivity.kt         # App entry point
│           │   ├── LoginActivity.kt        # Sign in screen
│           │   ├── RegisterActivity.kt     # Sign up screen
│           │   ├── HomeActivity.kt         # Habit dashboard
│           │   ├── AddHabitActivity.kt     # Add / edit habit
│           │   └── model/
│           │       └── Habit.kt            # Habit data model
│           └── res/
│               ├── layout/                 # XML UI layouts
│               └── values/                 # Strings, colors, themes
├── build.gradle
└── settings.gradle
```

---

## 🔑 Authentication Flow

```
Launch App
    │
    ▼
Sign In Screen ──── No account? ──► Register Screen
    │                                       │
    ▼                                       ▼
 Log In ◄──────────────────────────── Register
    │
    ▼
Home Dashboard (Habit List)
    │
    ├── + Add Habit
    ├── ✏️  Edit Habit
    ├── ✅  Check off Habit
    └── 🗑️  Delete Habit
```

---

## 🧩 Usage

### Adding a Habit

1. Tap the **+ Add Habit** button on the dashboard
2. Enter the habit name and a goal (e.g., *"Joggat — 10 mil"*)
3. Confirm to save

### Editing a Habit

1. Tap the ✏️ **pencil icon** on a habit card
2. Update the details
3. Save changes

### Deleting a Habit

1. Tap the 🗑️ **trash icon** on a habit card
2. The habit is permanently removed

### Logging Out

- Tap **LOG OUT** in the top-right corner of the dashboard

---

## 🤝 Contributing

Contributions are welcome! Please follow these steps:

1. Fork the repository
2. Create a new branch: `git checkout -b feature/your-feature-name`
3. Commit your changes: `git commit -m "Add your feature"`
4. Push to the branch: `git push origin feature/your-feature-name`
5. Open a Pull Request

---

