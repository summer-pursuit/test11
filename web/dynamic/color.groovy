output = """
<html>
<head>
    <title>Change Background Color</title>
    <script type="text/javascript">
    function changeBackgroundColor() {
        var colors = ["#FF5733", "#33FF57", "#3357FF", "#F333FF", "#33FFF5", "#F5FF33"];
        var randomColor = colors[Math.floor(Math.random() * colors.length)];
        document.body.style.backgroundColor = randomColor;
    }
    </script>
</head>
<body>
    <button onclick="changeBackgroundColor()">Change Background Color</button>
</body>
</html>
"""