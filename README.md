### Hexlet tests and linter status:
[![Actions Status](https://github.com/ilshatshamsetdinov/java-project-78/workflows/hexlet-check/badge.svg)](https://github.com/ilshatshamsetdinov/java-project-78/actions)
[![Maintainability](https://api.codeclimate.com/v1/badges/a65a3a52aea244ebe235/maintainability)](https://codeclimate.com/github/ilshatshamsetdinov/java-project-78/maintainability)
[![Test Coverage](https://api.codeclimate.com/v1/badges/a65a3a52aea244ebe235/test_coverage)](https://codeclimate.com/github/ilshatshamsetdinov/java-project-78/test_coverage)
[![Java CI](https://github.com/ilshatshamsetdinov/java-project-78/actions/workflows/workflows.yml/badge.svg)](https://github.com/ilshatshamsetdinov/java-project-78/actions/workflows/workflows.yml)

# Data Validator

## Description
Data validator is a library that can be used to check the correctness of any data.

### String validation:
* **required()** – any non-empty string
* **minLength(int number)** – string is equal to or longer than the number
* **contains(String substring)** – string contains a specific substring

---
![image](https://user-images.githubusercontent.com/119069422/229373962-d3c59cd4-71bc-4740-a3d0-dae4b9dd81f6.png)
---

### Numbers validation
* **required()** – any number including zero
* **positive()** – positive number
* **range(int number1, int number2)** – the range in which the numbers must fall, including the bounds

---
![image](https://user-images.githubusercontent.com/119069422/229374176-790ab113-6e50-47cc-8e02-1aab958ffe23.png)
---

### Maps validation
* **required()** – data type Map is required
* **sizeof(int number)** – the number of key-value pairs in the Map object must be equal to the specified number

---
![image](https://user-images.githubusercontent.com/119069422/229374339-0a6a3d7f-68b2-4e4b-bd73-dd019a83a721.png)
---

### Nested Validation
* Validate the data inside Map

---
![image](https://user-images.githubusercontent.com/119069422/229374658-37803f2e-0e41-4ebf-8bf1-a4b3cc83649f.png)
---
