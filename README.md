# Elinext Test
### Original requirements

- It may works like HomeScreen 7x10 items (add 2 pt between images)
on a smartphone with horizontal pagination.
- The top bar should contain two buttons: "+" and "Reload All".
- On "+" button it should add a new image to the end of the list.
- Reload all function should remove all existing images and load 140
new. Images should be rounded with corner radius = 7.
- Collection should scroll without any lags or freezes and be smooth.
- Also you need to appear activity indicator for cell if image not loaded
yet.
- You can get new images by executing this request "https://
picsum.photos/200/200". Each request will response you with different
image.

### Proof of completed
[proof_compressed.webm](https://github.com/user-attachments/assets/88b5292f-2fd4-407a-9f6b-7bc1eb919a5b)

### Project architecture

```
--- Structure ---

     +---------+
     |  Koin   |
     +---------+
       /     \
      /       \
+-------------+ +-----------------+
| HomeConfigs | | HomeViewModel   |
| (Singleton) | |   (ViewModel)   |
+-------------+ +-----------------+
                      |
                      v
             +-----------------+
             |   HomePage(UI)  |
             +-----------------+

- Koin provides both HomeConfigs (Singleton) and HomeViewModel.
- HomeViewModel is connected to HomePage (UI).
```
```

--- UI Layers Design ---

          +-----------------+
          |   HomePage      |
          +-----------------+
            /            \
+-----------------+  +-----------------+
|   TopBar        |  | HorizontalPager |
+-----------------+  +-----------------+
                      /              \
          +-----------------+   +-----------------+
          |   PageGrid      |   |   PageGrid      |  ....
          +-----------------+   +-----------------+
              /          \
+-----------------+  +-----------------+
|   GridItem      |  |   GridItem      |  ....
+-----------------+  +-----------------+

- HorizontalPager renders PageGird base on number of images in 
state, each page contains maximum 70 items
- PageGrid renders with fixed row = 7
- GridItem is implemented by AsyncImage composable, handle network
image automaticaly, custom logic to handle loading state and retry mechanism
```

### Tech stack
- **Kotlin** – programming language for Android
- **Jetpack Compose** – UI
- **Clean Architecture**
- **ViewModel**
- **StateFlow / Flow** – reactive state handling
- **Coil (AsyncImage)** – load images asynchronously with caching
- **Retry / Loading States** – custom implementation
- **Android Instrumented Tests**





