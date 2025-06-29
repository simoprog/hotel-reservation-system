# 🏨 Hotel Reservation System

## 📋 Overview

This project implements a simplified Hotel Reservation System in Java as part of Skypay's technical assessment. The system manages hotel rooms, users, and bookings with comprehensive validation and error handling.

## 🎯 Features

- **Room Management**: Create and update rooms with different types (Standard, Junior, Suite)
- **User Management**: Handle user accounts with balance tracking
- **Booking System**: Complete reservation system with availability checking
- **Data Integrity**: Historical snapshots preserve booking information
- **Validation**: Comprehensive input validation and exception handling
- **Reporting**: Detailed output of rooms, bookings, and users


## 📊 Test Results

The application runs the following test scenarios:

### Test Case Execution
![test-cases-hotel](https://github.com/user-attachments/assets/6f76a955-8b09-4c61-b6ac-e45d71917ede)


### Final Results Output
![final-results-hotel](https://github.com/user-attachments/assets/c5b8f4de-3d15-4833-b8b3-6191e2c46db0)



## 🏆 Design Questions (Bonus)

### Question 1: Service Architecture
**Q: Suppose we put all the functions inside the same service. Is this the recommended approach? Please explain.**

**Answer:**

**No, this is not the recommended approach for larger, production-ready systems.** While acceptable for this simple exercise, a monolithic service has several limitations:

**Problems with Single Service Approach:**
- **Violates Single Responsibility Principle**: One class handling too many concerns
- **Poor Maintainability**: Changes in one area affect the entire service
- **Limited Testability**: Difficult to unit test individual components
- **No Separation of Concerns**: Business logic mixed with data access

**Benefits of Proper Architecture:**
- **Repository Pattern**: Separates data access from business logic
- **Dependency Injection**: Makes testing easier with mock repositories
- **Service Layer**: Each service has a single responsibility
- **Better Scalability**: Easy to add new features and modify existing ones

### Question 2: Room Update Strategy
**Q: In this design, we chose to have a function setRoom(..) that should not impact the previous bookings. What is another way? What is your recommendation? Please explain and justify.**
**Answer:**
**Current approach (Snapshot): Store room data copy in booking ✅ Recommended**
**Alternative approach (Room Versioning): Store room versions with effective dates**

- **More complex but normalized**
- **Requires joins for queries**
- **Better for frequent room changes**
