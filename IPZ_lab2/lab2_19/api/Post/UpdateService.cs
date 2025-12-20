using System;
using System.Threading.Tasks;

namespace lab2_19.api.Post
{
    public class UpdateService
    {
        // Емуляція відповіді сервера
        public static async Task<bool> UpdateServiceRequestAsync(string Name, int Price, int ServiceId)
        {
            await Task.Delay(300); // Імітація затримки запиту

            // Лог "успішного" оновлення сервісу
            Console.WriteLine("[MockServer] Інформацію про послугу оновлено:");
            Console.WriteLine($"ServiceId: {ServiceId}");
            Console.WriteLine($"Назва: {Name}");
            Console.WriteLine($"Ціна: {Price} грн");

            // Завжди успішна відповідь
            return true;
        }

        // Клас-заглушка для сумісності з оригінальним кодом
        public class ResponseWrapper
        {
            public bool success { get; set; }
            public string message { get; set; }
        }
    }
}