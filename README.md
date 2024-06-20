# Numerical Calculations Regex Parser

Welcome to the Numerical Calculations Regex Parser repository! This project is designed to parse and evaluate numerical expressions provided as regular expressions.


## Table of Contents

- [Introduction](#introduction)
- [Features](#features)
- [Installation](#installation)
- [Usage](#usage)
- [Examples](#examples)
- [License](#license)

## Introduction

This repository contains a parser that can interpret and evaluate complex numerical expressions written as regular expressions. It supports a variety of arithmetic operations including addition, subtraction, multiplication, division, and exponentiation.

## Features

- **Comprehensive Parsing**: Handles complex numerical expressions using regular expressions.
- **Arithmetic Operations**: Supports addition, subtraction, multiplication, division, and exponentiation.

## Installation

To install the parser, follow these steps:

## Implementation details:
- Language used: Java ,version 17
- Libraries used:
    - java.io.File
    - java.io.FileNotFoundException
    - java.util.List
    - java.util.Scanner
    - java.util.ArrayList
    - java.util.Stack
- Not implemented parts/known bugs : None
- All the code is written by myself except the documentation of the functions, that i was helped by Github Copilot

**Clone the repository**:
    ```sh
    git clone https://github.com/amorilla42/Numerical-Calculations-Regex-Parser.git
    ```
    
## Usage

To parse and evaluate a numerical expression, use the following command:

## Instructions to run the code:
- Go into the src directory "cd /FormalLanguagesCalculator/ExpressionTransformationProject/src"
- Change the content of test.txt to the expressions that you want to transform
- To build de project "make"
- To run the project  "make run"
- To delete the output files "make clean"


## Examples

```sh
echo "3 + 5 * (2 - 4)^2 / 3" > test.txt
make
make run
```

## License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for more details.
