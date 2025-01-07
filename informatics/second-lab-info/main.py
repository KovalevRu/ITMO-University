def decode_hamming(codeword):

 # Вычисляем проверочные биты
 p1 = int(codeword[0]) ^ int(codeword[2]) ^ int(codeword[4]) ^ int(codeword[6])
 p2 = int(codeword[1]) ^ int(codeword[2]) ^ int(codeword[5]) ^ int(codeword[6])
 p3 = int(codeword[3]) ^ int(codeword[4]) ^ int(codeword[5]) ^ int(codeword[6])

 # Проверяем наличие ошибки
 error_bit = p1 * 1 + p2 * 2 + p3 * 4

 # Исправляем ошибку, если она есть
 if error_bit != 0:
  corrected_codeword = list(codeword)
  corrected_codeword[error_bit - 1] = str(1 - int(corrected_codeword[error_bit - 1]))
  codeword = "".join(corrected_codeword)

 # Извлекаем информационные биты
 message = codeword[2] + codeword[4] + codeword[5] + codeword[6]

 return message, error_bit

# Ввод сообщения
codeword = input("Введите код Хэмминга (7 цифр 0 и 1): ")

# Проверка длины сообщения
if len(codeword) != 7:
 print("Ошибка: Длина сообщения должна быть равна 7.")
else:
 # Декодирование сообщения
 message, error_bit = decode_hamming(codeword)

 # Вывод результата
 if error_bit != 0:
  print(f"Исправленное сообщение: {message}")
  print(f"Ошибка обнаружена в бите {error_bit}")
 else:
  print(f"Исходное сообщение: {message}")
  print("Ошибок не обнаружено.")