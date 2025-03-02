# News App

An Android App that fetches real-time news and displays articles in a user-friendly interface. Users can easily browse through news, read the full articles in a WebView, save their favorite stories for later read, and even delete saved articles. With a clean, modular design and the use of Jetpack Compose, the app offers an engaging user experience while supporting key features like dark mode and a local database for saved content.

## Screenshots

| Home Screen | Home Screen | Home Screen |
| ------------- | ------------- | ------------- |
| <img src="https://github.com/user-attachments/assets/ddfe5ba6-c610-44cd-82ca-0b01e88d08f0" width="200" alt="Enter expense"> | <img src="https://github.com/user-attachments/assets/11bcc53c-747e-4a1e-8981-64bbbe106c1a" width="200" alt="View all expenses"> | <img src="https://github.com/user-attachments/assets/1300d6b6-d164-4812-a542-4a227f6e5a77" width="200" alt="View all expenses"> |

| Saved Articles Screen | Saved Articles Screen |
| ------------- | ------------- |
| <img src="https://github.com/user-attachments/assets/ba58a11c-fbea-42e6-bf7e-b9e5ee106b95" width="200" alt="View all expenses"> | <img src="https://github.com/user-attachments/assets/7c2af6a1-5e04-4309-bcbf-e5bc62ae91e1" width="200" alt="View all expenses"> |

| Web View Screen | Web View Screen |
| ------------- | ------------- |
| <img src="https://github.com/user-attachments/assets/88ac7943-3461-40ef-a180-a1a690cc1321" width="200" alt="Category 1"> | <img src="https://github.com/user-attachments/assets/d869e991-43eb-4d82-8d52-56cb9dd47a05" width="200" alt="Category 2"> |

| Dark Mode | Dark Mode |
| ------------- | ------------- |
| <img src="https://github.com/user-attachments/assets/5ba92c7d-b04a-471d-9056-c9adfc367578" width="200" alt="Expense Summary"> | <img src="https://github.com/user-attachments/assets/8366db53-c087-454a-ae8d-f6b8f3f006fc" width="200" alt="Expense Summary"> |

## Libraries used

1. Dagger Hilt: Simplifies dependency injection by providing a standardized way to inject dependencies throughout the app.
2. Retrofit: Used for seamless API integration to fetch the latest news articles from NewsAPI.
3. Glide: Efficiently loads and displays images in image views, enhancing the user experience.
4. Constraint Layout: Used in Jetpack Compose for flexible and responsive UI layout design.
5. Android Navigation: Implements Android's type-safe navigation to manage screen transitions and deep linking.
6. Room: Local database to store and manage saved articles, ensuring offline access.


## Designing Patterns Used

1. MVVM Pattern: Separates the UI logic (View), business logic (ViewModel), and data handling (Model) to enhance testability and maintainability.
2. Repository Pattern: Acts as a single source of truth for data, handling data retrieval from multiple sources (API, database) and providing a clean interface for the ViewModel.
3. CLEAN Architecture: Follows a layered approach where each component (UI, domain, data) is independent, promoting scalability, testability, and separation of concerns.

## Installation

1. Clone the repository.
2. Open the project in Android Studio.
3. Run the app on an emulator or device.

## License

This project is not licensed. All rights reserved.
