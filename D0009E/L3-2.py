#add a word and its definition to the dictionary
def insert(dict):
    word = input("Enter word to insert: ")
    definition = input("Enter definition: ")
    
    # Check if the word already exists
    if word in dict:
        print(f"The word '{word}' already exists in the dictionary.")
    else:
        dict[word] = definition
        print(f"Inserted '{word}': '{definition}' into the dictionary.")
        
#lookup a word in the dictionary and print its definition
def lookup(dict):
    word = input("Enter word to lookup: ")
    
    # Check if the word exists and print its definition
    if word in dict:
        print(f"You looked up {word}: {dict[word]}")
    else:
        print(f"The word '{word}' was not found in the dictionary.")

#delete a word from the dictionary
def delete(dict):
    word = input("Enter word to delete: ")
    
    # Check if the word exists and delete it
    if word in dict:
        del dict[word]
        print(f"Deleted the word '{word}' from the dictionary.")
    else:
        print(f"The word '{word}' was not found in the dictionary.")
        
#main function to run the menu
def main_dic():
    dict = {}
    
    #Display menu until user chooses to exit
    while True:
        print("\n")
        print("Menu for Dictionary")
        print("1. Insert")
        print("2. Lookup")
        print("3. Delete")
        print("4. Exit")
        
        input_choice = int(input("Enter your choice: "))
        
        #Call the appropriate function based on user choice
        if input_choice == 1:
            insert(dict)
        elif input_choice == 2:
            lookup(dict)
        elif input_choice == 3:
            delete(dict)
        elif input_choice == 4:
            print("Exiting the program.")
            break
        else:
            print("Invalid choice. Please try again.")
            
# Run the main function
main_dic()