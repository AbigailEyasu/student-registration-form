# How to Run and See the Student Registration System v2.0

## 📋 Quick Start Guide

### Step 1: Clone the Repository
```bash
git clone https://github.com/AbigailEyasu/student-registration-form.git
cd student-registration-form
```

### Step 2: Install Dependencies
```bash
mvn clean install
```

### Step 3: Run the Application
```bash
mvn javafx:run
```

Or alternatively:
```bash
mvn clean compile
mvn exec:java -Dexec.mainClass="com.example.studentregistration.studentRegistrationForm"
```

---

## 🖥️ What You'll See (GUI Walkthrough)

### **Screen 1: HOME PAGE**
When you run the app, you'll see:
```
┌─────────────────────────────────────────────┐
│   STUDENT REGISTRATION SYSTEM v2.0          │
├─────────────────────────────────────────────┤
│                                             │
│  👋 Welcome to Student Registration System  │
│  ✅ Database-Backed Version                 │
│                                             │
│  Features:                                  │
│  • Register new students                    │
│  • View & Search all students               │
│  • Delete student records                   │
│  • Student login verification               │
│                                             │
│  ┌──────────────────────────────────────┐  │
│  │  Register New Student       [BUTTON]  │  │
│  │  View All Students          [BUTTON]  │  │
│  │  Student Login              [BUTTON]  │  │
│  └──────────────────────────────────────┘  │
│                                             │
└─────────────────────────────────────────────┘
```

### **Screen 2: REGISTER NEW STUDENT**
Click "Register New Student" to see:
```
┌─────────────────────────────────────────────┐
│   STUDENT REGISTRATION                      │
├─────────────────────────────────────────────┤
│                                             │
│  First Name: [________________]             │
│  Last Name:  [________________]             │
│  Age:        [_______]                      │
│  Year:       [Select Year ▼]                │
│  Gender:     (●) Male  ( ) Female           │
│  Email:      [________________]             │
│  Phone:      [________________]             │
│  Emergency:  [________________]             │
│                                             │
│  [Submit Registration]  [Back]              │
│                                             │
└─────────────────────────────────────────────┘
```

**Fill in the form and click Submit → You'll see:**
```
┌──────────────────────────────────┐
│  ✅ Registration Successful!     │
│                                  │
│  Name: John Doe                  │
│  Student ID: NaScR/5432/25       │
│  Year: II                        │
│                                  │
│  [OK]                            │
└──────────────────────────────────┘
```

### **Screen 3: VIEW ALL STUDENTS**
Click "View All Students" to see:
```
┌────────────────────────────────────────────────────┐
│   STUDENT MANAGEMENT                               │
├────────────────────────────────────────────────────┤
│  Search: [Search by name, ID, email...] [Search]  │
│  Total Students: 1                                │
├────────────────────────────────────────────────────┤
│ Full Name      │ Student ID      │ Year │ Email   │
├────────────────────────────────────────────────────┤
│ John Doe       │ NaScR/5432/25   │ II   │ john@.. │
│ Jane Smith     │ NaScR/7890/25   │ III  │ jane@.. │
│ Mike Johnson   │ NaScR/1234/25   │ I    │ mike@.. │
│                                                    │
│  [Refresh]  [Delete Selected]  [Back]             │
│                                                    │
└────────────────────────────────────────────────────┘
```

### **Screen 4: SEARCH FUNCTIONALITY**
In the View All Students screen:
- Type a name/ID/email in the search box
- Click "Search"
- Results filter automatically
- Shows matching records

### **Screen 5: DELETE STUDENT**
- Select a student from the table
- Click "Delete Selected"
- Confirmation dialog appears:
```
┌──────────────────────────────────┐
│  Confirm Delete                  │
│                                  │
│  Delete John Doe?                │
│                                  │
│  [OK]  [Cancel]                  │
└──────────────────────────────────┘
```
- Click OK → Student removed from database

### **Screen 6: STUDENT LOGIN**
Click "Student Login" to see:
```
┌────────────────────────────────┐
│   STUDENT LOGIN                │
├────────────────────────────────┤
│                                │
│  Full Name: [________________] │
│  Student ID: [________________]│
│                                │
│  [Login]  [Back]               │
│                                │
└────────────────────────────────┘
```

**Enter credentials → You'll see:**
```
┌──────────────────────────────┐
│  ✅ Login Successful!        │
│  Welcome John Doe            │
│  [OK]                        │
└──────────────────────────────┘
```

---

## 📁 Database Storage

After you register students, a file is created:
```
student-registration-form/
└── student_registration.db  (SQLite database file)
```

This file stores all your student data **permanently**.

---

## 🎯 Complete Workflow Example

### **Test Case 1: Register & View**
1. Click "Register New Student"
2. Fill: Name=John, Age=20, Year=II, Email=john@email.com
3. Click Submit → See success message
4. Click "View All Students" → See John in the table
5. Database file created with data

### **Test Case 2: Search**
1. Have 3+ students registered
2. Go to "View All Students"
3. Search: "john"
4. See only John's record
5. Search: "II"
6. See all Year II students

### **Test Case 3: Delete**
1. Go to "View All Students"
2. Select John's row
3. Click "Delete Selected"
4. Confirm deletion
5. John removed from table and database

### **Test Case 4: Login**
1. Have registered students
2. Click "Student Login"
3. Enter registered student name and ID
4. See login success

---

## ✅ What Proves It's Working

✅ **Database file created** - `student_registration.db` appears  
✅ **Data persists** - Close and reopen app, data still there  
✅ **Table displays** - All students shown in tableview  
✅ **Search works** - Filter shows correct results  
✅ **Delete removes** - Student gone from table and database  
✅ **Login works** - Verifies credentials from database  

---

## 🚀 If You Have Issues

**Issue: App won't run**
```bash
# Install Java 11+
java -version

# Install Maven
mvn -version

# Clean and rebuild
mvn clean install
mvn javafx:run
```

**Issue: No database file**
- Register first student
- Database auto-creates

**Issue: TableView empty**
- Register students first
- Click Refresh button

---

## 📊 Summary

Your application is **fully functional** with:
- ✅ GUI that opens in a desktop window
- ✅ Database persistence (SQLite)
- ✅ All CRUD operations working
- ✅ Search functionality
- ✅ Delete with confirmation
- ✅ Professional UI styling

**Just run:** `mvn javafx:run` and see it work! 🎉
