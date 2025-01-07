import re


def get_mail_server(email):
    # Регулярное выражение для проверки email и извлечения почтового сервера
    match = re.match(r'^([a-zA-Z0-9._%+-]+)@([a-zA-Z0-9.-]+\.[a-zA-Z]{2,})$', email)

    if match:
        # Извлекаем почтовый сервер (часть после @)
        mail_server = match.group(2)
        return mail_server
    else:
        # Если email некорректен
        return "Некорректный email"


email1 = "user@example.com"
email2 = "invalid-email.com"
email3 = "user@sub.domain.co"
email4 = "user@site"
email5 = "name@company.org"
email6 = "kovalevgit@gmail.com"


print(get_mail_server(email1))  # Ожидаем example.com
print(get_mail_server(email2))  # Ожидаем Некорректный email
print(get_mail_server(email3))  # Ожидаем sub.domain.co
print(get_mail_server(email4))  # Ожидаем Некорректный email
print(get_mail_server(email5))  # Ожидаем company.org
print(get_mail_server(email6))  # Ожидаем gmail.com
