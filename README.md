# 📊 PPTX to PDF Converter

> A multithreaded, modern desktop application built with Java Swing that effortlessly converts PowerPoint presentations (.pptx, .ppt) into standard PDF documents using JODConverter and LibreOffice.

![Java](https://img.shields.io/badge/Java-ED8B00?style=flat&logo=openjdk&logoColor=white)
![GUI](https://img.shields.io/badge/UI-Java_Swing-blue.svg)
![Build](https://img.shields.io/badge/Build-Maven-C71A36.svg)
![Library](https://img.shields.io/badge/Engine-JODConverter_%7C_LibreOffice-success.svg)

---

## 🌟 Overview

**PPTX to PDF Converter** is a desktop utility designed to securely and locally convert Microsoft PowerPoint files into PDFs. 

Rather than relying on cloud APIs, this application uses **JODConverter** to communicate directly with a local installation of LibreOffice running invisibly in the background. This ensures 100% offline functionality, maximum privacy, and perfect document formatting preservation.

---

## ✨ Key Features

*   **🎛️ Modern Dark Theme UI:** Features a sleek, responsive interface built with Java Swing.
*   **⚙️ Headless LibreOffice Engine:** Automates LibreOffice in the background to handle complex document conversions without interrupting your workflow.
*   **⏳ Heavy-Duty Ready:** Built with a custom 5-minute task execution timeout to safely process massive, graphics-heavy presentation files.
*   **⚡ Multithreaded Processing:** Uses `SwingWorker` to ensure the GUI remains fluid and responsive during long conversions.
*   **🛡️ Smart Error Handling:** Automatically detects if LibreOffice is missing from the system and guides the user to install it.

---

## 🛠️ Tech Stack & Dependencies

*   **Language:** Java (JDK 11+)
*   **Build Tool:** Maven
*   **UI Framework:** Java Swing
*   **Core Libraries:** JODConverter Local
*   **System Requirement:** [LibreOffice](https://www.libreoffice.org/) MUST be installed on the host machine.

---

## 🚀 Quick Start

### 1. Prerequisites
1. Install the **Java Development Kit (JDK)**.
2. Install **[LibreOffice](https://www.libreoffice.org/download/download-libreoffice/)**. (The app will automatically detect its installation path on most standard Windows/Mac/Linux setups).

### 2. Clone the Repository
```bash
git clone [https://github.com/YOUR-USERNAME/pptx-to-pdf.git](https://github.com/YOUR-USERNAME/pptx-to-pdf.git)
cd pptx-to-pdf
```

### 3. Build with Maven
```bash
mvn clean install
```

### 4. Run the Application
You can run the compiled .jar file directly from the target folder:
```bash
java -jar target/PPTX_PDF-1.0-SNAPSHOT-jar-with-dependencies.jar
```
(Note: Ensure you run the jar that includes dependencies).

---

## 📸 How to Use
1. Launch the application.
2. Click Browse... to select your .pptx or .ppt file.
3. Click Convert to PDF.
4. The app will launch LibreOffice in the background, convert the file, and automatically save the resulting PDF in the exact same folder as your original presentation.
