from flask import Flask, send_from_directory, jsonify
import os
from flask_cors import CORS
import logging

# Initialize the Flask app
app = Flask(__name__)

# Enable CORS for all routes
CORS(app)

# Set up logging for debugging and error tracking
logging.basicConfig(level=logging.DEBUG)

# Define the two base directories for file access
BASE_DIR_1 = r"D:\wallpapers"  # <-- First folder path (e.g., images)
BASE_DIR_2 = r"D:\hard disk old datas\i drive\best of d"  # <-- Second folder path (e.g., documents)

def get_base_dir(directory_type):
    """
    Get the correct base directory based on the directory type.
    """
    return BASE_DIR_1

@app.route('/list/<directory_type>/<path:subpath>', methods=['GET'])
@app.route('/list/<directory_type>/', defaults={'subpath': ''}, methods=['GET'])  # Allow empty path
def list_files(directory_type, subpath):
    """
    List files in the given directory (subpath) within the selected BASE_DIR.
    If the directory doesn't exist, return an error message.
    """
    base_dir = get_base_dir(directory_type)
    if not base_dir:
        return jsonify({"error": "Invalid directory type"}), 400

    directory = os.path.join(base_dir, subpath)
    if os.path.exists(directory):
        # Only include files, not directories
        files = [f for f in os.listdir(directory) if os.path.isfile(os.path.join(directory, f))]
        logging.debug(f"Files in {directory}: {files}")
        return jsonify(files)
    else:
        logging.error(f"Directory not found: {directory}")
        return jsonify({"error": "Path does not exist"}), 404

@app.route('/image/<path:filepath>', methods=['GET'])
def serve_image(filepath):
    """
    Serve an image from the specified filepath within the selected BASE_DIR.
    Prevent directory traversal attacks.
    """
    base_dir = BASE_DIR_1
    if not base_dir:
        return jsonify({"error": "Invalid directory type"}), 400

    file_path = os.path.join(base_dir, filepath)
    
    # Sanitize the path to avoid path traversal attacks
    if not os.path.commonpath([file_path, base_dir]) == base_dir:
        logging.warning(f"Forbidden access attempt: {file_path}")
        return jsonify({"error": "Access to this file is forbidden"}), 403
    
    if os.path.exists(file_path) and os.path.isfile(file_path):
        logging.debug(f"Serving image: {file_path}")
        return send_from_directory(base_dir, filepath)  # Serve image without forcing download
    else:
        logging.error(f"File not found: {file_path}")
        return jsonify({"error": "File not found"}), 404

@app.route('/download/<directory_type>/<path:filepath>', methods=['GET'])
def download_file(directory_type, filepath):
    """
    Serve a file for download from the specified filepath within the selected BASE_DIR.
    Prevent directory traversal attacks.
    """
    base_dir = get_base_dir(directory_type)
    if not base_dir:
        return jsonify({"error": "Invalid directory type"}), 400

    file_path = os.path.join(base_dir, filepath)
    
    # Sanitize the path to avoid path traversal attacks
    if not os.path.commonpath([file_path, base_dir]) == base_dir:
        logging.warning(f"Forbidden access attempt: {file_path}")
        return jsonify({"error": "Access to this file is forbidden"}), 403
    
    if os.path.exists(file_path) and os.path.isfile(file_path):
        logging.debug(f"Serving file for download: {file_path}")
        return send_from_directory(base_dir, filepath, as_attachment=True)
    else:
        logging.error(f"File not found: {file_path}")
        return jsonify({"error": "File not found"}), 404

@app.errorhandler(404)
def page_not_found(error):
    return jsonify({"error": "Page not found"}), 404


if __name__ == "__main__":
    # Run the app on all network interfaces (host='0.0.0.0') at port 5000
    app.run(host='0.0.0.0', port=5000)

