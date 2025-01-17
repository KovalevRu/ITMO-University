import json
import xmltodict

def convert_with_xmltodict(input_path, output_path):
    # Чтение JSON-файла
    with open(input_path, "r", encoding="utf-8") as file:
        data = json.load(file)  # Загружаем JSON в словарь

    # Конвертация словаря в XML
    xml_data = xmltodict.unparse(data, pretty=True)

    # Запись результата в XML-файл
    with open(output_path, "w", encoding="utf-8") as file:
        file.write(xml_data)

if __name__ == "__main__":
    input_path = "input.json"
    output_path = "output_xmltodict.xml"
    convert_with_xmltodict(input_path, output_path)