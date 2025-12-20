using System;
using System.Threading.Tasks;

namespace lab2_19.api.Post
{
    public class DeleteBookingRequest
    {
        // Емуляція відповіді від сервера
        public static async Task<bool> DeleteBookingRequestAsync(int BookingId, int Amount, string Name)
        {
            await Task.Delay(300); // Імітація затримки запиту

            // Логування для тестування (імітація видалення)
            Console.WriteLine("[MockServer] Бронювання видалено успішно:");
            Console.WriteLine($"BookingId: {BookingId}");
            Console.WriteLine($"Amount: {Amount}");
            Console.WriteLine($"Name: {Name}");

            // Завжди успішна відповідь
            return true;
        }

        // Клас залишено для сумісності з оригінальним кодом
        public class ResponseWrapper
        {
            public bool success { get; set; }
            public string message { get; set; }
        }
    }
}