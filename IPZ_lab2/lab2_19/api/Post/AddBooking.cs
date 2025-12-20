using System.Threading.Tasks;
using lab2_19.Entity;

namespace lab2_19.api.Post
{
    public class AddBooking
    {
        // Емуляція відповіді сервера
        public static async Task<bool> AddBookingRequestAsync(string Name, string Phone, string Time, int ServiceId)
        {
            await Task.Delay(300); // Імітація затримки запиту

            // Лог "успішної" операції (для налагодження)
            Console.WriteLine($"[MockServer] Нове бронювання створено:");
            Console.WriteLine($"Ім'я: {Name}");
            Console.WriteLine($"Телефон: {Phone}");
            Console.WriteLine($"Час: {Time}");
            Console.WriteLine($"ServiceId: {ServiceId}");

            // Завжди успішний результат
            return true;
        }

        // Клас залишено для сумісності з попередньою структурою
        public class ResponseWrapper
        {
            public bool success { get; set; }
            public string message { get; set; }
        }
    }
}