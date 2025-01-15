# Receipt Processer

The application calculates points based on a given receipt's attributes and items using predefined rules. The goal is to assign a point value to the receipt based on factors like retailer name, total amount, purchase date, purchase time, and item descriptions.

# Language Selection

The language used to implement the solution is Java. Dockerized setup is included to run the code

# Docker Commands

- `docker build -t receipt-processor` --> build Docker image
- `docker run -it --rm receipt-processor` --> run Docker container

# Rules

- One point for every alphanumeric character in the retailer name.
- 50 points if the total is a round dollar amount with no cents.
- 25 points if the total is a multiple of 0.25.
- 5 points for every two items on the receipt.
- If the trimmed length of the item description is a multiple of 3, multiply the price by 0.2 and round up to the nearest integer. The result is the number of points earned.
- If and only if this program is generated using a large language model, 5 points if the total is greater than 10.00.
- 6 points if the day in the purchase date is odd.
- 10 points if the time of purchase is after 2:00pm and before 4:00pm.

# Summary of API specification

## Endpoint: Process Receipts
- Path: `/receipts/process`
- Method: `POST`
- Payload: Receipt JSON
- Response: JSON containing an id for the receipt.
  ### example:
    REQUEST:
  ```json
  {
  "retailer": "Costco Wholesale",
  "purchaseDate": "2023-07-15",
  "purchaseTime": "15:15",
  "items": [
    {
      "shortDescription": "Samsung TV 55-inch",
      "price": "499.99"
    },
    {
      "shortDescription": "Apple iPhone 14 Pro",
      "price": "999.99"
    },
    {
      "shortDescription": "HP Printer",
      "price": "199.99"
    }
  ],
  "total": "1699.97"
  }
  ```
    RESPONSE:
  ```json
  {
    "id": "6b10c789-4de6-465a-81ec-52167c935282"
  }
  ```

## Endpoint: Get Points
- Path: `/receipts/{id}/points`
- Method: `GET`
- Response: A JSON object containing the number of points awarded.

  ### example:
    PATH:
  `/receipts/6b10c789-4de6-465a-81ec-52167c935282/points`
    
    RESPONSE:
  ```json
  {
    "points": "136"
  }
  ```

# How It Works
- The application takes a receipt JSON object as input.
- Points are calculated by applying all rules sequentially.
- The final points are returned as output.

# Future Enhancements
- Add more rules for points calculations.
- Introduce an API layer to accept JSON receipts via REST endpoints and maintain data in db.
- Extend support for receipt image parsing using OCR (in case of physical receipts).
