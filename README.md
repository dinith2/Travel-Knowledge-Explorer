# Travel-Knowledge-Explorer

Upon clicking the "Click to perform first fetch" button:
Fetches data about countries from an API endpoint ('https://www.randyconnolly.com/funwebdev/3rd/api/travel/countries.php').
Populates the dropdown menu with the fetched country data.
Fetches images for all countries using another API endpoint ('https://www.randyconnolly.com/funwebdev/3rd/api/travel/images.php').
Displays loading animations while fetching data.
Upon selecting a country from the dropdown menu:
Fetches images specific to the selected country using the ISO country code.
Displays the fetched images on the webpage.
Utilizes CSS for styling, including background colors, font styles, button design, and image grid layout.
Incorporates JavaScript to handle DOM manipulation and interaction:
Listens for events like button clicks and dropdown selections.
Fetches data asynchronously from APIs using fetch() method.
Dynamically updates the content of the webpage based on user actions and fetched data.
