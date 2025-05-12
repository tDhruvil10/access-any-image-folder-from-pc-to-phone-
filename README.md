from pathlib import Path

readme_content = """# 📷 Android Image Viewer with Python Flask Backend

This project is a simple Android app that fetches a list of images from a local Python Flask server and displays them in a scrollable list. Tapping on an image opens it in full-screen view.

---

## 🧩 Features

- 📡 Fetch image list from local Flask server
- 🖼 Display images using `RecyclerView` and `Glide`
- 🔍 Full-screen image viewer
- 🔁 Real-time image updates supported via HTTP fetch

---

## 📱 Android App

### Structure

- **MainActivity.kt**: Fetches image list from Flask server and displays it in a `RecyclerView`.
- **ImageAdapter.kt**: Binds image URLs to the `RecyclerView` using Glide.
- **ImageViewerActivity.kt**: Displays selected image in full screen.
- **layout/item_image.xml**: Layout for each image item.
- **layout/activity_image_viewer.xml**: Layout containing full-screen `ImageView`.

### Dependencies

Make sure to include:

gradle
implementation 'com.github.bumptech.glide:glide:4.16.0'
implementation 'com.squareup.okhttp3:okhttp:4.12.0'
Python Flask Backend
Structure
py_server_dev_!.py: Flask app that:

Lists files from a given local directory

Serves image files via /image/<filename>

Supports CORS for local Android access

Setup
Install dependencies:

bash
Always show details

Copy
pip install Flask flask-cors
Edit the BASE_DIR_1 in py_server_dev_!.py to your image directory (e.g., D:\\wallpapers).

Run the server:

bash
Always show details

Copy
python py_server_dev_!.py
Server will be available at:
http://<your-ip>:5000

🔌 Endpoints
GET /list/image/ — Returns list of image filenames

GET /image/<filename> — Returns the image file

GET /download/image/<filename> — Downloads the image file

🚀 Running the Project
Android App
Ensure your PC and Android device are on the same WiFi network.

Replace all 192.168.29.188 IPs with your actual PC's local IP address.

Run the Flask server.

Launch the Android app on a device/emulator.

📁 Folder Access
You can configure the image base folder by modifying:

python
Always show details

Copy
BASE_DIR_1 = r"D:\\wallpapers"
Replace BASE_DIR_1 with your actual folder address.
🛡 Security Notes
Basic path traversal protection is implemented in Flask routes.

No authentication or permission model—intended for LAN use only.

📸 Screenshots
(You can add app screenshots here once available.)

🧑‍💻 Author
Developed by [Dhruvil Taratia].
Android + Python image viewer for LAN-based gallery access.
