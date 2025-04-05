# BabyHealthChecker 👶🩺　
by Misuzu Taniguchi (A01367008)

A simple Android app build with Jetpack Compose to help parents to track baby health data -- with a particular
focus on **stool tracking** and **food allergy info**.


## 🧩 Features

### 🚼 Excretion Log
- Record stool data including:
  - Date, time, shape, color, quantity, and notes
- Color-coded indicators with **health alerts** for suspicious stool colors
- Add new entries and refresh the log view

### 🍽️ Food Allergy Info Lookup
- Enter a **product barcode** and fetch:
  - Product name
  - Ingredients
  - Allergen information
- Data fetched using the OpenFoodFacts API


## 🗃️ Database (Room)

- **MyDatabase**: Singleton class that provides the Room database instance.
- **ExcretionDao**: DAO for CRUD operations on stool entry records.
- **ExcretionRepository**: Provides an abstraction over the DAO to interface with the UI.
- **LocalExcretion**: Data class representing stool entry data (date, time, shape, color, quantity, option).


## 🌐 API Integration

- **FoodRepository**: Uses [OpenFoodFacts API](https://world.openfoodfacts.org/data) to fetch product data by barcode.
- **Product**: Data class holding fields like product name, allergens, and ingredients.
- **MyHttpClient.kt**: Ktor-based HTTP client for making network requests.


## 🎨 UI (Jetpack Compose)

- **Top Navigation Bar**:
  - Implemented in `MyTopBar.kt`
  - Allows navigation between `Home.kt` and `AllergyInfo.kt`


## 🧠 State Management

- **ExcretionState.kt**  
  Handles business logic and interaction with the `ExcretionRepository`.

- **FoodState.kt**  
  Manages API calls and holds the fetched product data.

- **LogState.kt**  
  Holds UI state for form input fields (date, time, shape, color, quantity, option).
