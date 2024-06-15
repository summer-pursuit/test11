output = """
<html>
<head>
    <title>Generate Random Number</title>
    <script type="text/javascript">
    function generateRandomNumber() {
        var randomNumber = Math.floor(Math.random() * 100000) + 1; // Generate a random number between 1 and 100
        document.getElementById('randomNumberDisplay').innerText = "Random Number: " + randomNumber;
    }
    </script>
</head>
<body>
    <button onclick="generateRandomNumber()">Generate Random Number</button>
    <p id="randomNumberDisplay">Random Number: </p>
</body>
</html>
"""