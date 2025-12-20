using System;
using System.Threading.Tasks;

namespace lab2_19.api.Post
{
    public class EditBookingRequest
    {
        // Емуляція відповіді від сервера
        public static async Task<bool> EditBookingRequestRequestAsync(string Name, string Phone, string Time, int BookingId)
        {
            await Task.Delay(300); // Імітація затримки запиту

            // Вивід в консоль для наочності тестування
            Console.WriteLine("[MockServer] Дані бронювання оновлено:");
            Console.WriteLine($"BookingId: {BookingId}");
            Console.WriteLine($"Ім'я: {Name}");
            Console.WriteLine($"Телефон: {Phone}");
            Console.WriteLine($"Час: {Time}");

            // Завжди успішна відповідь
            return true;
        }

        // Клас залишено для сумісності зі старим кодом
        public class ResponseWrapper
        {
            public bool success { get; set; }
            public string message { get; set; }
        }
    }
}