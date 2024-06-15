// 定义一个函数来改变背景颜色
function color() {
    var colors = ["#FF5733", "#33FF57", "#3357FF", "#F333FF", "#33FFF5", "#F5FF33"];
    var randomColor = colors[Math.floor(Math.random() * colors.length)];
    document.body.style.color = randomColor;
}

// 生成HTML内容
var output = `
<html>
<head>
    <title>Change Background Color</title>
</head>
<body>
    <button onclick="color()">Change Background Color</button>
</body>
</html>
`;