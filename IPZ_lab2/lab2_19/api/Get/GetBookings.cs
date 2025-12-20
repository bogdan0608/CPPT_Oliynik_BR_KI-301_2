using System.Collections.ObjectModel;
using System.Text.Json;
using System.Threading.Tasks;
using lab2_19.Entity;

namespace lab2_19.api.Get
{
    public class GetBookings
    {
        // Емуляція відповіді замість реального запиту до сервера
        public static async Task<ObservableCollection<Booking>> GetBookingsResponseAsync()
        {
            await Task.Delay(300); // Імітація затримки запиту

            // Статичні тестові дані
            var mockBookings = new ObservableCollection<Booking>
            {
                new Booking("Іван Іванов", "+380991112233", "2025-10-24 10:00") { Id = 1 },
                new Booking("Марія Петренко", "+380671234567", "2025-10-24 11:30") { Id = 2 },
                new Booking("Олег Гутович", "+380931234567", "2025-10-24 14:00") { Id = 3 },
                new Booking("Софія Боднар", "+380501234567", "2025-10-25 09:15") { Id = 4 }
            };

            // Повертаємо симульовану відповідь
            return mockBookings;
        }

        // Клас-заглушка для сумісності зі старим кодом
        private class ResponseWrapper
        {
            public List<Booking> message { get; set; }
        }
    }
}