"""
main.py

Точка входу програми (в окремому модулі).
Демонстрація роботи базового і похідного класів "Принтер".
"""

from __future__ import annotations

from printer_pkg.printer_base import Printer
from printer_pkg.laser_printer import LaserPrinter


def main() -> None:
    # Базовий принтер
    base = Printer(model="Canon Basic-100", paper=5, toner=10)
    print("=== БАЗОВИЙ ПРИНТЕР ===")
    print(base.status())

    print(base.print_document("Lab report", 3))
    print("\nПісля друку:")
    print(base.status())

    # Лазерний принтер (похідний клас)
    laser = LaserPrinter(model="HP LaserJet Pro", paper=10, toner=5, eco_mode=True)
    print("=== ЛАЗЕРНИЙ ПРИНТЕР (ECO) ===")
    print(laser.status())

    print(laser.print_document("Contract", 6))
    print("\nПісля друку:")
    print(laser.status())

    laser.toggle_eco_mode()
    print("Перемкнули режим на NORMAL.\n")
    print(laser.print_document("Invoice", 2))
    print("\nФінальний стан:")
    print(laser.status())


if __name__ == "__main__":
    main()