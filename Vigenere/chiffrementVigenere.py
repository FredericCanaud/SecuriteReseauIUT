###############     Chiffrement de Vigenère    ############### 
# 1) On récupère toutes les lettres de l'alphabet en majuscule

al = "".join([ chr(97+i) for i in range(0,26) ]).upper()

# 2) On crée deux dictionnaires qui associent respectivement
# un nombre à une lettre et une lettre à un nombre

alphabet = {}
nombre = {}
for i in range(0,26):
    alphabet[i] = al[i]
    nombre[al[i]] = i

# 3) On demande à l'utilisateur le message à chiffrer ainsi
# que la clé de chiffrement, que l'on utilise en majuscule
# et sans espace pour ne pas donner d'indications précises

message = input("Entrez le message à chiffrer : ").upper().rstrip()
cle = input("Entrez la clé de chiffrement : ").upper()

# 4) Pour chaque caractère du message, on retourne la
# position de la lette du message plus la position de 
# la lettre de la clé correspondante dans l'alphabet

# On renvoie le reste de la division de cette position
# par 26 en cas de débordement au début ou à la fin de
# alphabet

for i in range(0,len(message)):
    print(alphabet[(nombre[message[i]] + nombre[cle[i%len(cle)]])%26])  