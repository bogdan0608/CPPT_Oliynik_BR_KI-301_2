"""
lab19_expr_io.py

Варіант 19.
Обчислення виразу:
    y = ctg(x) / (sin(2x) + 4cos(x)),
де ctg(x) = cos(x)/sin(x).

Функціонал модуля:
- calculate(x): обчислення y з перевірками області визначення
- write_text/read_text: запис/читання (x, y) у текстовому форматі
- write_binary/read_binary: запис/читання (x, y) у двійковому форматі

Примітки:
- x задається у радіанах
- у текстовому файлі зберігаються 2 рядки: x=..., y=...
- у двійковому файлі зберігаються 2 числа float64 (double) у little-endian
"""

from __future__ import annotations

import math
import struct
from typing import Tuple


_EPS = 1e-12  # допуск для перевірки "майже нуль"


class CalculationError(ValueError):
    """Помилка обчислення (наприклад, ділення на нуль або вихід за область визначення)."""


def ctg(x: float) -> float:
    """
    Обчислює ctg(x) = cos(x)/sin(x) з перевіркою sin(x) != 0.

    :param x: аргумент у радіанах
    :return: ctg(x)
    :raises CalculationError: якщо sin(x) == 0 (або дуже близько до нуля)
    """
    s = math.sin(x)
    if abs(s) < _EPS:
        raise CalculationError("ctg(x) не визначена: sin(x) = 0")
    return math.cos(x) / s


def calculate(x: float) -> float:
    """
    Обчислює y = ctg(x)/(sin(2x) + 4cos(x)) з перевірками області визначення.

    :param x: аргумент у радіанах
    :return: значення y
    :raises CalculationError: якщо знаменник дорівнює 0 або ctg(x) не визначена
    """
    denom = math.sin(2.0 * x) + 4.0 * math.cos(x)
    if abs(denom) < _EPS:
        raise CalculationError("Ділення на нуль: sin(2x) + 4cos(x) = 0")
    return ctg(x) / denom


def write_text(path: str, x: float, y: float) -> None:
    """
    Записує результат у текстовий файл.

    Формат:
        x=<значення>
        y=<значення>

    :param path: шлях до файлу
    :param x: аргумент
    :param y: результат
    """
    with open(path, "w", encoding="utf-8") as f:
        f.write(f"x={x:.17g}\n")
        f.write(f"y={y:.17g}\n")


def read_text(path: str) -> Tuple[float, float]:
    """
    Зчитує (x, y) з текстового файлу формату:
        x=...
        y=...

    :param path: шлях до файлу
    :return: (x, y)
    :raises ValueError: якщо формат некоректний
    """
    with open(path, "r", encoding="utf-8") as f:
        lines = [line.strip() for line in f.readlines() if line.strip()]

    if len(lines) < 2 or not lines[0].startswith("x=") or not lines[1].startswith("y="):
        raise ValueError("Некоректний формат текстового файлу (очікується x=... та y=...)")

    x = float(lines[0].split("=", 1)[1])
    y = float(lines[1].split("=", 1)[1])
    return x, y


def write_binary(path: str, x: float, y: float) -> None:
    """
    Записує результат у двійковий файл як два float64 (double) little-endian.

    :param path: шлях до файлу
    :param x: аргумент
    :param y: результат
    """
    data = struct.pack("<dd", x, y)
    with open(path, "wb") as f:
        f.write(data)


def read_binary(path: str) -> Tuple[float, float]:
    """
    Зчитує (x, y) з двійкового файлу, де збережено два float64 (double) little-endian.

    :param path: шлях до файлу
    :return: (x, y)
    :raises ValueError: якщо файл має неправильну довжину
    """
    with open(path, "rb") as f:
        data = f.read()

    if len(data) != 16:
        raise ValueError("Некоректний двійковий файл: очікується 16 байт (2 * float64)")

    x, y = struct.unpack("<dd", data)
    return x, y


def main() -> None:
    """
    Демонстраційний запуск:
    - зчитати x
    - обчислити y
    - записати у text і bin
    - прочитати назад і показати
    """
    try:
        x_str = input("Введіть x (радіани): ").strip()
        if not x_str:
            print("Помилка: x не введено. Завершення.")
            return

        x = float(x_str)
        y = calculate(x)

        text_path = "result.txt"
        bin_path = "result.bin"

        write_text(text_path, x, y)
        write_binary(bin_path, x, y)

        x_t, y_t = read_text(text_path)
        x_b, y_b = read_binary(bin_path)

        print("\nОбчислення успішне.")
        print(f"y = {y:.10g}")
        print(f"Записано у файли: {text_path}, {bin_path}")
        print("\nПеревірка читання:")
        print(f"TXT -> x={x_t:.10g}, y={y_t:.10g}")
        print(f"BIN -> x={x_b:.10g}, y={y_b:.10g}")

    except CalculationError as e:
        print(f"Помилка обчислення: {e}")
    except ValueError:
        print("Помилка: некоректне число для x.")
    except OSError as e:
        print(f"Помилка файлових операцій: {e}")


if __name__ == "__main__":
    main()