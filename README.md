NSE Announcements Scraper

This is a Java and Selenium automation tool designed to download corporate announcement filings from the NSE India website.

Workflow of tool : 

This tool script automates the manual process of fetching weekly corporate announcements for a specific company. It solves the problem by:

Launching a Chrome browser and navigating to the NSE's "Corporate Filings & Announcements" page.

Searching for a pre-defined company ticker (e.g., "RELIANCE") and selecting it.

Filtering the announcements by the "1W" (1 Week) timeframe.

Downloading the resulting data as a .csv file.

Saving the file automatically to a downloads folder in the project's root directory.

How to Run

Prerequisites

Java JDK 11 or newer

Apache Maven

Google Chrome browser

Steps to Execute

Clone the Repository:

git clone [your-repo-url]
cd [your-repo-name]


Build the Project:
Open a terminal in the project's root directory (where pom.xml is) and run:

mvn install


(This will download Selenium and all other necessary dependencies.)

Run the Scraper:
Once the build is complete, run the following command:

mvn exec:java -Dexec.mainClass="NseAnnouncementsScraper"


Find Your File:
The script will create a downloads folder in the project directory and save the .csv file inside it.
