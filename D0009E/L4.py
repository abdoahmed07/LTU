"""
class board:
    def __init__(self, message):
        self.message = message
        
    def change_message(self, new_message):
        self.message += " " + new_message
        
        
        
from IPython.display import clear_output
    
board1 = board("Hello")
board2 = board("Goodbye")


kim = board1
chris = board2

while True:

    clear_output()
    if kim == board1:
        print(f"Kim reads message: Board1: {kim.message}")
    else:
        print(f"Kim reads message: Board2: {kim.message}")
    if chris == board1:
        print(f"Chris reads message: Board1: {chris.message}")
    else:
        print(f"Chris reads message: Board2: {chris.message}")
    print("1: Direct Kim to other board")
    print("2: Direct Chris to other board")
    print("3: Tell Kim to post a message")
    print("4: Tell Chris to post a message")
    print("0: Exit")
            
    input_choice = int(input("Enter choice: "))
    
        
    if input_choice == 1:
        if kim == board1:
            kim = board2
        else:
            kim = board1
    elif input_choice == 2:
        if chris == board1:
            chris = board2
        else:
            chris = board1
    elif input_choice == 3:
        new_message = input("Enter new message for Kim to post: ")
        kim.change_message(new_message)
    elif input_choice == 4:
        new_message = input("Enter new message for Chris to post: ")
        chris.change_message(new_message)
    elif input_choice == 0:
        print("Exiting program.")
        break
    
        """




class phoneBook:
    def __init__(self):
        # number -> list of names (aliases)
        self.entries = {}

    # Adds a new entry to the phone book with name and number
    # If number not in phone book, create new entry
    # because its not there then it will check if name is already in the list of names for that number
    # if name is already there, it will print that name already exists for that number. Which is not going to happen because we checked if number is not in phone book
    # if name is not there, it will append the name to the list of names for that number
    def add(self, name, number):
        if number not in self.entries:
            self.entries[number] = []
        if name in self.entries[number]:
            print(f"{name} already exists for {number}")
        else:
            self.entries[number].append(name)
            print(f"Added {name} with number {number}")

    # Looks up a name and prints the associated number(s)
    def lookup(self, name):
        # Find all numbers for the name if it exists
        matches = [num for num, names in self.entries.items() if name in names]
        if not matches:
            print(f"{name} not found")
        elif len(matches) == 1:
            print(f"{name}'s number is {matches[0]}")
        else:
            print(f"{name} has multiple numbers: {', '.join(matches)}")

    # Add alias for existing name
    def alias(self, existing_name, new_name, number=None):
        matches = [num for num, names in self.entries.items() if existing_name in names]
        if not matches:
            print(f"{existing_name} not found")
            return
        if len(matches) > 1 and number is None:
            print(f"Multiple entries for {existing_name}, please specify the number")
            return
        if number is None:
            number = matches[0]
        if new_name in self.entries[number]:
            print(f"{new_name} already exists for {number}")
        else:
            self.entries[number].append(new_name)
            print(f"Added alias {new_name} for {existing_name} with number {number}")

    # Change number for all aliases
    def change(self, name, new_number, old_number=None):
        matches = [num for num, names in self.entries.items() if name in names]
        if not matches:
            print(f"{name} not found")
            return
        if len(matches) > 1 and old_number is None:
            print(f"Multiple entries for {name}, please specify the old number")
            return
        if old_number is None:
            old_number = matches[0]

        if name not in self.entries.get(old_number, []):
            print(f"{name} not found with number {old_number}")
            return

        if new_number == old_number:
            print(f"Changed {name}'s number from {old_number} to {new_number}")
            return

        alias_group = self.entries[old_number]
        if new_number not in self.entries:
            self.entries[new_number] = []

        for nm in alias_group:
            if nm not in self.entries[new_number]:
                self.entries[new_number].append(nm)

        del self.entries[old_number]
        print(f"Changed {name}'s number from {old_number} to {new_number}")

    # Remove one name/number pair
    #needs fixing, only removes the number and name and not the aliases as well
    def remove(self, name, number):
        if number not in self.entries or name not in self.entries[number]:
            print(f"{name} with number {number} not found")
            return
        self.entries[number].remove(name)
        if not self.entries[number]:
            del self.entries[number]
        print(f"Removed {name} with number {number}")

    # List all entries
    def list_entries(self):
        for num, names in self.entries.items():
            print(f"{num}: {', '.join(names)}")

    # Save phonebook
    def save(self, filename):
        try:
            with open(filename, 'w') as f:
                for number, names in self.entries.items():
                    f.write(number + ";" + ";".join(names) + ";\n")
            print(f"Phonebook saved to '{filename}'.")
        except FileNotFoundError:
            print(f"Error: The file '{filename}' could not be found.")
        except PermissionError:
            print(f"Error: Permission denied when trying to save to '{filename}'.")
        except Exception as e:
            print(f"Unexpected error while saving: {e}")

    def load(self, filename):
        try:
            with open(filename, 'r') as f:
                self.entries.clear()
                for line in f:
                    parts = line.strip().split(';')
                    if len(parts) < 2:
                        continue
                    number, names = parts[0], parts[1:-1]
                    self.entries[number] = names
            print(f"Phonebook loaded from '{filename}'.")
        except FileNotFoundError:
            print(f"Error: The file '{filename}' was not found.")
        except PermissionError:
            print(f"Error: Permission denied when trying to read '{filename}'.")
        except Exception as e:
            print(f"Unexpected error while loading: {e}")

    def run(self):
        # Did it this way to avoid a long if-elif-else chain, makes it easier to add new commands and keeps the code cleaner
        commands = {
            "add":    lambda a: self.add(a[0], a[1]) if len(a) == 2 else print("Usage: add <name> <number>"),
            "lookup": lambda a: self.lookup(a[0]) if len(a) == 1 else print("Usage: lookup <name>"),
            "alias":  lambda a: (
                self.alias(a[0], a[1], a[2]) if len(a) == 3
                else self.alias(a[0], a[1]) if len(a) == 2
                else print("Usage: alias <existing_name> <new_name> [number]")
            ),
            "change": lambda a: (
                self.change(a[0], a[1], a[2]) if len(a) == 3
                else self.change(a[0], a[1]) if len(a) == 2
                else print("Usage: change <name> <new_number> [old_number]")
            ),
            "remove": lambda a: self.remove(a[0], a[1]) if len(a) == 2 else print("Usage: remove <name> <number>"),
            "save":   lambda a: self.save(a[0]) if len(a) == 1 else print("Usage: save <filename>"),
            "load":   lambda a: self.load(a[0]) if len(a) == 1 else print("Usage: load <filename>"),
            "list":   lambda a: self.list_entries() if len(a) == 0 else print("Usage: list"),
        }

        # ask for user input in a loop
        while True:
            user_input = input("phoneBook> ").strip()
            if not user_input:
                continue
            parts = user_input.split()
            action = parts[0].lower()
            args = parts[1:]

            if action == "quit":
                print("Exiting phone book.")
                break

            if action in commands:
                commands[action](args)
            else:
                print("Unknown command. Available:", ", ".join(commands.keys()))




while True:
    phoneBook().run()
