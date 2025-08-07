# 🌍 Countries API - Backend Testing Suite

This project contains a comprehensive suite of automated tests for the Countries REST API. The goal is to verify the correctness, structure, and data integrity of responses using RestAssured, JSON Schema validation, and generate detailed reports using Allure.

---

## ✅ Features Covered

### 1. 📋 List of Countries
- Verifies that the API returns a complete list of countries.
- Checks the structure and presence of required fields (`name`, `code`, etc.).
- Asserts HTTP response code and content type.

### 2. 📐 Schema Validation
- Validates the structure of the API response against a predefined JSON schema (`countries-schema.json`).
- Ensures backward compatibility and consistent data format.

### 3. 🔍 Confirmation of Specific Countries
- Confirms that specific countries (e.g., `France`, `Japan`, `Brazil`) are present in the API response.
- Uses `JsonPath` assertions for dynamic checking.

### 4. 🈯 Validate Languages
- Validates the presence and correctness of language-related data (e.g., official language codes and names).
- Ensures each country object includes a `languages` field with valid entries.

### 5. 📊 Allure Report Integration
- Generates detailed Allure reports for every test run.
- Provides insights into test coverage, steps, and failures (if any).
- Easy-to-use UI for viewing assertions, request/response, and schema validations.
- Locating reports in the `allure-results` directory after test execution http://127.0.0.1:64557/#suites/8399274b4bb69ec8699907dd6ef7de4e
- Run `allure serve` to view the report in your browser.
- Supports custom tags and descriptions for better organization.
- Allows filtering and grouping of test results.
- Supports screenshots and attachments for failed tests.
- Provides a timeline of test execution.
- Supports parameterized tests for different scenarios.
- ![Allure Report Screenshot] (/Users/lence/Desktop/Automation/RestAssured3rdGroup2025/img.png)

---

## 🧪 Technology Stack

- **Java**
- **JUnit 5**
- **RestAssured**
- **Hamcrest Matchers**
- **JSON Schema Validator**
- **Allure Report**

---

## 🗂 Project Structure

