using System;
using System.Threading.Tasks;

namespace lab2_19.api.Post
{
    public class ResetScore
    {
        // Емуляція відповіді сервера
        public static async Task<bool> ResetScoreRequestAsync(string Name)
        {
            await Task.Delay(300); // Імітація затримки запиту

            // Імітація "скидання" балів
            Console.WriteLine("[MockServer] Рахунок користувача скинуто успішно:");
            Console.WriteLine($"Ім'я користувача: {Name}");

            // Завжди повертаємо успішний результат
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