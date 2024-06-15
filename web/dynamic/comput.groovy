output = """
<html>
<head>
    <title>Full Button Calculator</title>
    <script type="text/javascript">
    var currentInput = "";
    var previousInput = "";
    var operation = null;
    
    function inputDigit(digit) {
        currentInput += digit;
        document.getElementById('display').value = currentInput;
    }
    
    function inputOperation(op) {
        if (operation !== null) calculate();
        previousInput = currentInput;
        currentInput = "";
        operation = op;
    }
    
    function calculate() {
        var result;
        var prev = parseFloat(previousInput);
        var current = parseFloat(currentInput);
        
        switch(operation) {
            case '+':
                result = prev + current;
                break;
            case '-':
                result = prev - current;
                break;
            case '*':
                result = prev * current;
                break;
            case '/':
                if(current !== 0) {
                    result = prev / current;
                } else {
                    result = 'Error: Division by zero';
                }
                break;
            default:
                result = 'Invalid operation';
        }
        
        currentInput = result.toString();
        operation = null;
        document.getElementById('display').value = currentInput;
    }
    
    function clearAll() {
        currentInput = "";
        previousInput = "";
        operation = null;
        document.getElementById('display').value = "";
    }
    </script>
</head>
<body>
    <h1>Full Button Calculator</h1>
    <input type="text" id="display" readonly>
    <br>
    <button onclick="inputDigit('1')">1</button>
    <button onclick="inputDigit('2')">2</button>
    <button onclick="inputDigit('3')">3</button>
    <button onclick="inputOperation('+')">+</button>
    <br>
    <button onclick="inputDigit('4')">4</button>
    <button onclick="inputDigit('5')">5</button>
    <button onclick="inputDigit('6')">6</button>
    <button onclick="inputOperation('-')">-</button>
    <br>
    <button onclick="inputDigit('7')">7</button>
    <button onclick="inputDigit('8')">8</button>
    <button onclick="inputDigit('9')">9</button>
    <button onclick="inputOperation('*')">*</button>
    <br>
    <button onclick="clearAll()">C</button>
    <button onclick="inputDigit('0')">0</button>
    <button onclick="calculate()">=</button>
    <button onclick="inputOperation('/')">/</button>
</body>
</html>
"""