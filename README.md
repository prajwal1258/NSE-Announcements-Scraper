📋 NSE Announcements Scraper
A Java + Selenium automation tool that downloads corporate announcement filings from the NSE India website with zero manual effort.

🔍 What It Does
This tool automates the tedious process of fetching weekly corporate announcements for a specific company. Here's the full workflow:

Launches Chrome and navigates to NSE's Corporate Filings & Announcements page.
Searches for a company by ticker symbol (e.g., RELIANCE) and selects it.
Filters by timeframe — sets the view to the last 1 Week (1W).
Downloads the results as a .csv file.
Saves the file automatically to a downloads/ folder in the project root.


⚙️ Prerequisites
Make sure you have the following installed before running the tool:
RequirementVersionJava JDK11 or newerApache MavenLatest stableGoogle ChromeLatest stable

🚀 Getting Started
1. Clone the Repository
bashgit clone [your-repo-url]
cd [your-repo-name]
2. Build the Project
Run the following from the project root (where pom.xml lives). This downloads Selenium and all other required dependencies:
bashmvn install
3. Run the Scraper
Once the build is complete, execute:
bashmvn exec:java -Dexec.mainClass="NseAnnouncementsScraper"
```

The downloaded `.csv` file will appear in the `downloads/` folder in your project root.

---

## 📁 Output
```
project-root/
└── downloads/
    └── NSE_announcements_<date>.csv

🛠️ Tech Stack

Java — Core application logic
Selenium WebDriver — Browser automation
Apache Maven — Dependency management & build tool
ChromeDriver — Chrome browser interface



Note: The target company ticker is pre-defined in the source code. To scrape announcements for a different company, update the ticker symbol in NseAnnouncementsScraper.java before building.
 Sonnet 4.6
