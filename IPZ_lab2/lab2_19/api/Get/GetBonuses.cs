using System.Text.Json;
using System.Threading.Tasks;
using lab2_19.Entity;

namespace lab2_19.api.Get
{
    public class GetBonuses
    {
        // Емуляція відповіді від "сервера"
        public static async Task<(bool, UserScore)> GetBonusesResponseAsync(string Name)
        {
            await Task.Delay(300); // Імітація затримки запиту до сервера

            // Статичні дані для симуляції
            var mockUser = new UserScore
            {
                Id = 1,
                Name = Name,
                Score = 1200
            };

            // Емуляція успішної відповіді
            return (true, mockUser);
        }
    }
}