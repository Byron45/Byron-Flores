def cifrado_cesar():
    # Definimos el alfabeto español
    alfabeto = "ABCDEFGHIJKLMNÑOPQRSTUVWXYZ"

    # Validamos entrada de la cadena
    cadena = input("Ingresa la cadena de caracteres a cifrar: ").strip().upper()
    if not cadena:
        print("Error: No ingresaste ningún texto para cifrar.")
        return

    # Validamos entrada de n
    entrada_n = input("Ingresa el valor de desplazamiento (n): ").strip()
    if not entrada_n:
        print("Error: No ingresaste ningún valor para el desplazamiento.")
        return

    if not entrada_n.isdigit():
        print("Error: El valor de desplazamiento debe ser un número entero no negativo.")
        return

    n = int(entrada_n)
    if n < 0:
        print("Error: No se puede utilizar un número negativo como desplazamiento.")
        return

    n = n % len(alfabeto)
    alfabeto_cifrado = alfabeto[n:] + alfabeto[:n]

    resultado = ""
    for caracter in cadena:
        if caracter in alfabeto:
            indice = alfabeto.index(caracter)
            resultado += alfabeto_cifrado[indice]
        else:
            resultado += caracter

    print("\n--- Resultado ---")
    print("Alfabeto original: ", alfabeto)
    print("Alfabeto cifrado:  ", alfabeto_cifrado)
    print("Cadena ingresada:  ", cadena)
    print("Cadena cifrada:    ", resultado)

if __name__ == "__main__":
    cifrado_cesar()
