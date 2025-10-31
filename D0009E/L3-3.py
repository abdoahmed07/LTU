#add a word and its definition to the dictionary
def insert(entries):
    word = input("Enter word to insert: ").strip()
    definition = input("Enter definition: ").strip()
    
    # Check if word already exists
    for w, d in entries:
        if w == word:
            print(f"'{word}' already exists.")
            return
    entries.append((word, definition))
    print(f"Inserted '{word}': '{definition}'")

#lookup a word in the dictionary and print its definition
def lookup(entries):
    word = input("Enter word to lookup: ").strip()
    
    for w, d in entries:
        if w == word:
            print(f"You looked up {word}: {d}")
            return
    print(f"'{word}' not found.")

#delete a word from the dictionary
def delete(entries):
    word = input("Enter word to delete: ").strip()
    
    for i, (w, d) in enumerate(entries):
        if w == word:
            entries.pop(i)
            print(f"Deleted '{word}'.")
            return
    print(f"'{word}' not found.")

#main function to run the menu
def main_tuples():
    entries = [] 
    
    while True:
        print("Menu")
        print("1. Insert")
        print("2. Lookup")
        print("3. Delete")
        print("4. Exit")
        choice = input("Enter choice: ").strip()
        if choice == "1": insert(entries)
        elif choice == "2": lookup(entries)
        elif choice == "3": delete(entries)
        elif choice == "4":
            print("Exiting.")
            break
        else:
            print("Invalid choice.")

# Run the main function
main_tuples()