/*
  課題4 KEVIN 24B39020
*/
package para.calc;

import javafx.application.Application;
import javafx.stage.*;
import javafx.scene.*;
import javafx.geometry.Pos;
import javafx.scene.shape.*;
import javafx.scene.paint.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import java.util.Stack;

/**
 * JavaFX 電卓アプリケーションのメインクラス
 */
public class Calculator extends Application {
  /** 入力数式表示用ラベル */
  Label input;
  /** 計算結果表示用ラベル */
  Label output;
  /** 数式格納用バッファ */
  StringBuilder buff;
  /** 計算エンジン */
  Executor ex;

  /** デフォルトコンストラクタ */
  public Calculator() {
    input = new Label();
    output = new Label();
    buff = new StringBuilder();
    ex = new Executor1();
  }

  /** 格子状に置かれる電卓のボタン名 */
  String[] buttonname = { "9", "8", "7", "+",
      "6", "5", "4", "-",
      "3", "2", "1", "*",
      "0", ".", ",", "/" };

  public static Double reverse_polish(StringBuilder buff) {

    Stack<Double> stack = new Stack<>();
    String[] items = buff.toString().split(",");
    for (String item : items) {
      if (item.equals("+") || item.equals("*")
          || item.equals("-") || item.equals("/")) {
        Double operandA = stack.pop();
        Double operandB = stack.pop();

        switch (item) {
          case "+":
            stack.push(operandA + operandB);
            break;
          case "-":
            stack.push(operandB - operandA);
            break;
          case "*":
            stack.push(operandA * operandB);
            break;
          case "/":
            stack.push(operandB / operandA);
            break;
        }
      } else {
        stack.push(Double.parseDouble(item));
      }
    }

    return stack.pop();
  }

  /** JavaFX 起動 */
  public void start(Stage stage) {
    VBox root = new VBox();
    root.setAlignment(Pos.CENTER);
    GridPane grid = new GridPane();
    Scene scene = new Scene(root, 200, 300);
    Button[] buttons = new Button[16];
    Button buttoncal = new Button("=");
    buttoncal.setPrefHeight(56);
    Button buttondel = new Button("<");
    buttondel.setPrefHeight(56);
    StackPane stack = new StackPane();
    stack.getChildren().add(new Rectangle(140, 30, Color.WHITE));
    stack.getChildren().add(input);
    root.getChildren().addAll(stack, output);
    // my code
    HBox Hb0 = new HBox();
    HBox Hb1 = new HBox();
    HBox Hb2 = new HBox();
    HBox Hb3 = new HBox();
    int i;
    for (i = 0; i < 16; i++) {
      buttons[i] = new Button(buttonname[i]);
      buttons[i].setPrefSize(28, 28);
      buttons[i].setOnAction(e -> {
        Button b = (Button) e.getSource();
        buff.append(b.getText());
        input.setText(buff.toString());

      });

    }

    buttoncal.setOnAction(event -> {
      try {
        double ans = reverse_polish(buff);
        output.setText(String.valueOf(ans));
        buff.setLength(0);
        input.setText("");

      } catch (Exception e) {
        output.setText("Invalid Syntax");
      }

    });

    buttondel.setOnAction(event -> {
      if (buff.length() > 0) {
        buff.setLength(buff.length() - 1);
        input.setText(buff.toString());
      }

    });

    Hb0.getChildren().addAll(buttons[0], buttons[1], buttons[2], buttons[3]);
    Hb1.getChildren().addAll(buttons[4], buttons[5], buttons[6], buttons[7]);
    Hb2.getChildren().addAll(buttons[8], buttons[9], buttons[10], buttons[11]);
    Hb3.getChildren().addAll(buttons[12], buttons[13], buttons[14], buttons[15]);
    VBox numbers = new VBox();
    numbers.getChildren().addAll(Hb0, Hb1, Hb2, Hb3);

    VBox symbols = new VBox();
    symbols.getChildren().addAll(buttoncal, buttondel);

    HBox calculator = new HBox();
    calculator.getChildren().addAll(numbers, symbols);
    calculator.setAlignment(Pos.CENTER);

    root.getChildren().addAll(calculator);
    stage.setWidth(200);
    stage.setHeight(200);
    stage.setScene(scene);
    stage.setTitle("JavaFX Calc");
    stage.show();
  }
}
