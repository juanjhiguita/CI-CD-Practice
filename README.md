*Juan José Higuita Sánchez*

*POM PRACTICE DESCRIPTION*

You are automating the https://www.saucedemo.com/ web application.
Create a new Selenium - Java - TestNG project, and configure it to launch and test the
required webpage on Chrome browser. Implement on your framework the following
scenarios, each one as a different test:

- *Purchase a product:* Follow the complete buyflow of the page, selecting a
random product, adding it to the cart, adding the personal data, and check you
are successfully arriving at the “Thank you for your purchase” page.
- *Removing elements of the shopping cart:* Add 3 different elements to the
shopping cart, enter to the cart page, remove them and check the shopping cart
is empty.
- *Logout:* try to log out and check if you are correctly redirected to the login page.

For this implementation, follow the rules described next:

- Page Object Model should be correctly implemented, making sure to reuse
certain elements and define base pages as needed for common methods and
elements.
- Page factory is required.
- Before annotations should be used to manage preconditions. Any other required
annotations can be implemented as needed.
