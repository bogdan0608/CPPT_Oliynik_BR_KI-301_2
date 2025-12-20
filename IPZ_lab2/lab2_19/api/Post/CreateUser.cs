using System;
using System.Threading.Tasks;

namespace lab2_19.api.Post
{
    public class CreateUser
    {
        // Емуляція відповіді сервера
        public static async Task<bool> CreateUserRequestAsync(string Name)
        {
            await Task.Delay(300); // Імітація затримки запиту

            // Логування "створення користувача" для тестування
            Console.WriteLine("[MockServer] Створено нового користувача:");
            Console.WriteLine($"Ім'я: {Name}");

            // Завжди успішна відповідь
            return true;
        }

        // Клас залишено для сумісності з початковою структурою
        public class ResponseWrapper
        {
            public bool success { get; set; }
            public string message { get; set; }
        }
    }
}