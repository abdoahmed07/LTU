#add a word and its definition to the dictionary
def insert(words, defs):
    word = input("Enter word to insert: ").strip()
    definition = input("Enter definition: ").strip()
    if word in words:
        print(f"'{word}' already exists.")
        return
    words.append(word)
    defs.append(definition)
    print(f"Inserted '{word}': '{definition}'")

#lookup a word in the dictionary and print its definition
def lookup(words, defs):
    word = input("Enter word to lookup: ").strip()
    if word in words:
        i = words.index(word)
        print(f"You looked up {word}: {defs[i]}")
    else:
        print(f"'{word}' not found.")

#delete a word from the dictionary
def delete(words, defs):
    word = input("Enter word to delete: ").strip()
    if word in words:
        i = words.index(word)
        words.pop(i)
        defs.pop(i)
        print(f"Deleted '{word}'.")
    else:
        print(f"'{word}' not found.")


#main function to run the menu
def main_lists():
    words = []
    defs  = []

    while True:
        print("Menu")
        print("1. Insert")
        print("2. Lookup")
        print("3. Delete")
        print("4. Exit")
        choice = input("Enter choice: ").strip()
        if choice == "1": insert(words, defs)
        elif choice == "2": lookup(words, defs)
        elif choice == "3": delete(words, defs)
        elif choice == "4":
            print("Exiting.")
            break
        else:
            print("Invalid choice.")


# Run the main function
main_lists()
