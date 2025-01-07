def dec_to_neg_dec(decimal_number):

  if decimal_number == 0:
    return 0

  result = []
  while decimal_number != 0:
    remainder = decimal_number % (-10)
    decimal_number //= (-10)

    if remainder < 0:
      remainder += 10
      decimal_number += 1

    result.append(str(remainder))

  return "".join(result[::-1])

decimal_number = int(input("Введите число в 10 СС: "))

neg_dec_number = dec_to_neg_dec(decimal_number)

print(f"Число в 10 СС: {decimal_number}")
print(f"Число в -10 СС: {neg_dec_number}")