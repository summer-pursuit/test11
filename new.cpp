#include <iostream>
#include <algorithm>
using namespace std;

int main() {
    double num1, num2, num3;
    cout << "请输入";
    cin >> num1 >> num2 >> num3;

    double maxNum = max(max(num1, num2), num3);
    double minNum = min(min(num1, num2), num3);
    double avgNum = (num1 + num2 + num3) / 3;

    cout << "最大值" << maxNum << "\n";
    cout << "最小值" << minNum << "\n";
    cout << "平均值" << avgNum << "\n";

    return 0;
}