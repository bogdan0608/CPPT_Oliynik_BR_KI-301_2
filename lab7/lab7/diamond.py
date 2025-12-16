"""
diamond_jagged.py

Лаба: генерація зубчатого списку (jagged list), який містить лише заштриховану
область квадратної матриці N×N для фігури "ромб".

Вимоги:
- N і символ-заповнювач вводяться з клавіатури
- якщо символ не введено або введено кілька символів — коректне завершення
- формується зубчатий список, де кожен рядок має довжину ширини ромба у цьому рядку
"""

from __future__ import annotations


def generate_diamond_jagged(n: int, fill: str) -> list[list[str]]:
    """
    Генерує зубчатий список для ромба в квадраті N×N.
    Зубчатість: у кожному рядку зберігаються лише символи ромба (без пробілів поза ним).

    Формула:
        center = n // 2
        dist = abs(i - center)
        width = n - 2*dist

    :param n: розмір квадратної матриці (n > 0)
    :param fill: один символ-заповнювач
    :return: зубчатий список (list of lists) з елементами ромба
    """
    center = n // 2
    jagged: list[list[str]] = []

    for i in range(n):
        dist = abs(i - center)
        width = n - 2 * dist
        if width < 1:
            width = 1
        jagged.append([fill] * width)

    return jagged


def print_as_square(jagged: list[list[str]], n: int) -> None:
    """
    Друкує зубчатий список як квадрат N×N: поза ромбом — пробіли.

    :param jagged: зубчатий список ромба
    :param n: розмір квадратної матриці
    """
    center = n // 2

    for i in range(n):
        dist = abs(i - center)
        left_spaces = dist
        width = len(jagged[i])

        line = (" " * left_spaces) + ("".join(jagged[i]))
        # добиваємо пробілами до N для візуальної квадратності
        line = line.ljust(n, " ")
        print(line)


def main() -> None:
    """
    Зчитує N і символ, перевіряє коректність, генерує зубчатий список для ромба
    та виводить результат у консоль.
    """
    try:
        n_str = input("Введіть розмір квадратної матриці N (N > 0): ").strip()
        if not n_str:
            print("Помилка: N не введено. Завершення.")
            return

        n = int(n_str)
        if n <= 0:
            print("Помилка: N має бути > 0. Завершення.")
            return
    except ValueError:
        print("Помилка: N має бути цілим числом. Завершення.")
        return

    fill = input("Введіть символ-заповнювач (рівно 1 символ): ")
    # Коректне переривання при не введенні або введенні кількох символів
    if fill is None or len(fill) != 1:
        print("Помилка: потрібно ввести рівно 1 символ. Завершення.")
        return

    jagged = generate_diamond_jagged(n, fill)

    print("\nЗубчатий список (внутрішнє представлення):")
    print(jagged)

    print("\nРомб у вигляді квадратної матриці N×N:")
    print_as_square(jagged, n)


if __name__ == "__main__":
    main()