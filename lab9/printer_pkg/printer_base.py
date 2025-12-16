"""
printer_base.py

Базовий клас предметної області "Принтер".
Містить стан (папір, тонер), базові операції (додати папір, замінити тонер, друк).
"""

from __future__ import annotations


class Printer:
    """
    Базовий принтер.

    Атрибути:
        model (str): модель принтера
        paper (int): кількість аркушів у лотку
        toner (int): рівень тонеру у відсотках (0..100)
        printed_pages (int): лічильник надрукованих сторінок
    """

    def __init__(self, model: str, paper: int = 0, toner: int = 100) -> None:
        if not model:
            raise ValueError("model не може бути порожнім")
        if paper < 0:
            raise ValueError("paper не може бути від'ємним")
        if toner < 0 or toner > 100:
            raise ValueError("toner має бути в діапазоні 0..100")

        self.model = model
        self.paper = paper
        self.toner = toner
        self.printed_pages = 0

    def add_paper(self, sheets: int) -> None:
        """Додає папір у лоток."""
        if sheets <= 0:
            raise ValueError("Кількість аркушів має бути > 0")
        self.paper += sheets

    def replace_toner(self) -> None:
        """Заміна/заправка тонеру (до 100%)."""
        self.toner = 100

    def status(self) -> str:
        """Повертає читабельний статус принтера."""
        return (
            f"Принтер: {self.model}\n"
            f"  Папір: {self.paper} арк.\n"
            f"  Тонер: {self.toner}%\n"
            f"  Надруковано: {self.printed_pages} стор.\n"
        )

    def _can_print(self, pages: int) -> None:
        """
        Внутрішня перевірка перед друком.
        У базовій моделі: 1 сторінка = 1 аркуш, 1% тонеру.
        """
        if pages <= 0:
            raise ValueError("pages має бути > 0")
        if self.paper < pages:
            raise RuntimeError("Недостатньо паперу для друку")
        if self.toner < pages:
            raise RuntimeError("Недостатньо тонеру для друку")

    def print_document(self, title: str, pages: int) -> str:
        """
        Друк документа.

        :param title: назва документа
        :param pages: кількість сторінок
        :return: повідомлення про успішний друк
        """
        if not title:
            raise ValueError("title не може бути порожнім")

        self._can_print(pages)

        # Витрати ресурсів
        self.paper -= pages
        self.toner -= pages
        self.printed_pages += pages

        return f"Надруковано документ '{title}' ({pages} стор.) на {self.model}."