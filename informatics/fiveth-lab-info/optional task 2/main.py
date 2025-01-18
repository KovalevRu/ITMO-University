import pandas as pd

# Задаем параметры
isu_number = 00
filename = 'SPFB.RTS-12.18_180901_181231.csv'
output_filename = 'vivod.csv'

# Шаг 1: Вычисление дня
remainder = isu_number % 27
selected_day = remainder + 1
if selected_day > 28:
    selected_day -= 27

# Шаг 2: Загрузка данных
data = pd.read_csv(filename, delimiter=',')

# Преобразуем дату в формат datetime
data['<DATE>'] = pd.to_datetime(data['<DATE>'], format='%d/%m/%y')

# Шаг 3: Выбор дней из каждого месяца
selected_data = pd.DataFrame()
for month in [9, 10, 11, 12]:
    month_data = data[data['<DATE>'].dt.month == month]
    selected_day_in_month = month_data[month_data['<DATE>'].dt.day == selected_day]

    if selected_day_in_month.empty:  # Если выбранный день отсутствует, прибавляем 2
        selected_day_in_month = month_data[month_data['<DATE>'].dt.day == selected_day + 2]

    selected_data = pd.concat([selected_data, selected_day_in_month])

# Шаг 4: Сохранение результата
selected_data.to_csv(output_filename, index=False)

print(f"Данные за выбранные дни сохранены в файл: {output_filename}")