# Student Registration System - Version 2.0
## Database-Backed Application with SQLite

### 🎯 Overview

Enhanced Student Registration System with **SQLite database persistence**, built with Java and JavaFX. This version demonstrates professional software development practices including database integration, CRUD operations, and the Data Access Object (DAO) pattern.

---

### ✨ New Features in v2.0

#### 📊 **Database Persistence**
- SQLite database stores student information permanently
- Data persists between application sessions
- Automatic database initialization on first run
- Unique student ID constraint for data integrity

#### 📋 **View All Students**
- TableView displays all registered students
- Shows name, student ID, year, and email
- Total student count displayed
- Refresh button to update data in real-time

#### 🔍 **Enhanced Student Login**
- Login using full name and student ID
- Verify credentials against database records
- Access student profile after successful login

#### 📝 **Improved Registration**
- All student information stored in SQLite database
- Unique student ID generation (NaScR/XXXX/25 format)
- Complete input validation
- Age range validation (16-99)
- Email format validation

---

### 🏗️ Project Structure

```
student-registration-form/
├── pom.xml                                    # Maven configuration
├── src/
│   └── main/java/com/example/studentregistration/
│       ├── studentRegistrationForm.java      # Main GUI application (updated v2.0)
│       ├── Student.java                      # Enhanced Student model class
│       ├── DatabaseManager.java              # SQLite connection manager
│       └── StudentDAO.java                   # Data Access Object (CRUD)
├── README.md                                  # Documentation
└── student_registration.db                   # SQLite database (auto-created)
```

---

### 🛠️ Technologies Used

| Technology | Version | Purpose |
|-----------|---------|---------|
| Java | 11+ | Programming language |
| JavaFX | 21 | GUI framework |
| SQLite | 3.43 | Database |
| Maven | 3.6+ | Build tool |
| JDBC | - | Database connectivity |

---

### 💾 Database Schema

**Students Table:**
```sql
CREATE TABLE students (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    full_name TEXT NOT NULL,
    student_id TEXT UNIQUE NOT NULL,
    year TEXT NOT NULL,
    age INTEGER NOT NULL,
    gender TEXT NOT NULL,
    email TEXT NOT NULL,
    phone_number TEXT NOT NULL,
    emergency_contact TEXT NOT NULL,
    registered_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
)
```

---

### 🚀 Installation & Running

#### Prerequisites
- Java Development Kit (JDK) 11 or higher
- Maven 3.6 or higher
- Git

#### Step-by-Step Setup

1. **Clone the repository:**
   ```bash
   git clone https://github.com/AbigailEyasu/student-registration-form.git
   cd student-registration-form
   ```

2. **Checkout the database-backend branch:**
   ```bash
   git checkout feature/database-backend
   ```

3. **Install dependencies:**
   ```bash
   mvn clean install
   ```

4. **Run the application:**
   ```bash
   mvn javafx:run
   ```

   **OR** compile and run directly:
   ```bash
   mvn clean compile
   mvn exec:java -Dexec.mainClass="com.example.studentregistration.studentRegistrationForm"
   ```

---

### 📖 Usage Guide

#### 1️⃣ Register a New Student
- Click **"Register New Student"** on the home page
- Fill in all required information:
  - First Name and Last Name
  - Age (16-99)
  - Year of study (I-VII)
  - Gender (Male/Female)
  - Email address (must contain @)
  - Phone number
  - Emergency contact
- Click **"Submit Registration"**
- Student data is saved to the SQLite database immediately
- Success message displays with generated Student ID

#### 2️⃣ View All Registered Students
- Click **"View All Students"** on the home page
- TableView displays all registered students with:
  - Full Name
  - Student ID
  - Year of Study
  - Email
- Total student count shown at the top
- Click **"Refresh"** to reload latest data from database

#### 3️⃣ Student Login
- Click **"Student Login"** on the home page
- Enter your full name and student ID
- System verifies credentials against database records
- Login successful message appears
- Access to student profile

---

### 🔄 Comparison: v1.0 vs v2.0

| Feature | v1.0 | v2.0 |
|---------|------|------|
| **Data Storage** | In-memory only | ✅ SQLite Database |
| **Multiple Students** | ❌ 1 at a time | ✅ Unlimited |
| **Data Persistence** | ❌ Lost on exit | ✅ Permanent |
| **View All Students** | ❌ Not available | ✅ TableView |
| **CRUD Operations** | Limited (Reg+Login) | ✅ Full support |
| **Error Handling** | Basic | ✅ Enhanced logging |
| **Code Organization** | Single file | ✅ Modular (DAO pattern) |
| **Professional Level** | Basic | ✅ Advanced |

---

### 📚 Key Classes

#### **Student.java**
- Enhanced model class with all student fields
- Two constructors:
  - New student (without database ID)
  - Existing student (from database with ID)
- Complete getters and setters
- LocalDateTime support for registration date

#### **DatabaseManager.java**
- Manages SQLite JDBC connection
- Auto-creates tables on initialization
- Connection lifecycle management
- Logging support

#### **StudentDAO.java** (Data Access Object)
Implements complete CRUD operations:
- `addStudent()` - Create new student record
- `getAllStudents()` - Read all students
- `getStudentByStudentId()` - Read specific student
- `updateStudent()` - Update student information
- `deleteStudent()` - Delete student record
- `getTotalStudentCount()` - Get count of all students

#### **studentRegistrationForm.java**
- Main JavaFX application class
- Five scenes: Home, Register, Login, View All, Profile
- Database integration throughout
- Professional UI styling and validation

---

### 🎓 Learning Outcomes

This project demonstrates:

✅ **Database Design**
- Schema design and optimization
- Unique constraints and relationships

✅ **JDBC & SQL**
- Database connections
- PreparedStatements for security
- ResultSet handling

✅ **Design Patterns**
- DAO (Data Access Object) pattern
- Separation of concerns
- MVC-like architecture

✅ **Java Best Practices**
- Logging (java.util.logging)
- Resource management (try-with-resources)
- Exception handling

✅ **GUI Development**
- JavaFX GUI framework
- TableView component
- Scene management

---

### 🔧 Troubleshooting

**Q: Database connection error?**
- Ensure SQLite JDBC driver is in classpath
- Check Maven dependencies: `mvn dependency:resolve`
- Verify database file created: `student_registration.db`

**Q: JavaFX module not found?**
- Use Maven: `mvn javafx:run`
- Or add VM options: `--module-path /path/to/javafx-sdk/lib --add-modules javafx.controls,javafx.fxml`

**Q: No students displayed in table?**
- Register at least one student first
- Click "Refresh" button
- Check database file permissions

**Q: Student ID not showing after registration?**
- Close and reopen the app
- Click "View All Students"
- The ID should appear in the table

---

### 🎯 Future Enhancement Ideas

- [ ] Password hashing for secure login
- [ ] Admin panel for student management
- [ ] Export to CSV/Excel
- [ ] Search and filter students
- [ ] Course enrollment tracking
- [ ] Grade management system
- [ ] Email notifications
- [ ] Attendance tracking
- [ ] Report generation

---

### 🤝 Contributing

Contributions are welcome! To contribute:

1. Fork the repository
2. Create a feature branch: `git checkout -b feature/your-feature`
3. Make your changes
4. Commit: `git commit -m 'Add feature description'`
5. Push: `git push origin feature/your-feature`
6. Open a Pull Request

---

### 📄 License

This project is open source and available under the **MIT License**.

---

### 👤 Author

**Abigail Eyasu**  
- GitHub: [@AbigailEyasu](https://github.com/AbigailEyasu)
- Email: eyasuabigiya622@gmail.com

---

### 📞 Support & Questions

- 📧 Open an issue on GitHub
- 💬 Check existing documentation
- 📖 Review code comments and docstrings

---

**Version:** 2.0  
**Last Updated:** June 2025  
**Status:** ✅ Production Ready  
**Branch:** `feature/database-backend`
