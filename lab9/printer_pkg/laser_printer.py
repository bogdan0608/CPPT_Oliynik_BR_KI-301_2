"""
laser_printer.py

Похідний клас LaserPrinter.
Відрізняється від базового принтера витратою тонеру:
- наприклад, "eco_mode" зменшує витрати тонеру
"""

from __future__ import annotations

from printer_pkg.printer_base import Printer


class LaserPrinter(Printer):
    """
    Лазерний принтер (похідний клас).

    Додатково:
        eco_mode (bool): якщо True — витрата тонеру менша
    """

    def __init__(self, model: str, paper: int = 0, toner: int = 100, eco_mode: bool = False) -> None:
        super().__init__(model=model, paper=paper, toner=toner)
        self.eco_mode = eco_mode

    def toggle_eco_mode(self) -> None:
        """Перемикає еко-режим."""
        self.eco_mode = not self.eco_mode

    def _toner_cost(self, pages: int) -> int:
        """
        Визначає витрати тонеру.

        Приклад:
        - звичайний режим: 1% тонеру на 1 сторінку
        - eco_mode: 1% тонеру на 2 сторінки (округлення вгору)
        """
        if self.eco_mode:
            return (pages + 1) // 2
        return pages

    def _can_print(self, pages: int) -> None:
        """
        Перевизначена перевірка для LaserPrinter з урахуванням тонеру.
        """
        if pages <= 0:
            raise ValueError("pages має бути > 0")
        if self.paper < pages:
            raise RuntimeError("Недостатньо паперу для друку")
        toner_need = self._toner_cost(pages)
        if self.toner < toner_need:
            raise RuntimeError("Недостатньо тонеру для друку (з урахуванням режиму)")

    def print_document(self, title: str, pages: int) -> str:
        """
        Перевизначений друк документа з еко-режимом.

        :return: повідомлення про друк + режим
        """
        if not title:
            raise ValueError("title не може бути порожнім")

        self._can_print(pages)

        toner_need = self._toner_cost(pages)

        self.paper -= pages
        self.toner -= toner_need
        self.printed_pages += pages

        mode = "ECO" if self.eco_mode else "NORMAL"
        return (
            f"Надруковано '{title}' ({pages} стор.) на {self.model} | режим: {mode} | "
            f"витрачено тонеру: {toner_need}%."
        )