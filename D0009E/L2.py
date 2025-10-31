import math

def bounce(n):
    print(n)
    if n > 0:
        bounce(n - 1)
        print(n)

def tvarsumman(n):
    if n < 10:
        return n
    else:
        return n % 10 + tvarsumman(n // 10)
    
def bounce2(n):
    # first count down
    for i in range(n, -1, -1):
        print(i)
    # then count up
    for i in range(1, n + 1):
        print(i)
        
def tvarsumman2(n):
    total = 0
    while n > 0:
        total += n % 10
        n //= 10
    return total

def solve(f, x0, eps):
    x = x0
    while abs(f(x)) > eps:
        x = x - f(x) / (2 * x)  # f'(x) = 2x
    return x

# Example usage:
bounce(5)
bounce2(5) 
tvarsumman(12345)
tvarsumman2(12345)


def derivative(f, x, h):
    return (f(x + h) - f(x - h)) / (2 * h)

# Newton's method to find a root of f starting from x0 with step size h
def solve(f, x0, h):
    while True:
        x1 = x0 - f(x0) / derivative(f, x0, h)
        if abs(x1 - x0) < h:
            return x1
        x0 = x1
        
f1 = lambda x: x**2 - 1
print("Root near 2:", solve(f1, 2, 1e-6))    # expected ~1
print("Root near -2:", solve(f1, -2, 1e-6))  # expected ~-1


f2 = lambda x: 2**x - 1
print("Root:", solve(f2, 10, 1e-6))          # expected ~0


f3 = lambda x: x - math.e**(-x)
print("Root:", solve(f3, 1, 1e-6))           

# Verify the solution for f3
print (solve(f3, 1, 1e-6) - math.e**(-solve(f3, 1, 1e-6)))  




################LAB 3######################
def iii():
    while True:  # loop indefinitely until exit
        print("\nChoose:")
        print(" 1. Give a number to bounce")
        print(" 2. Give a number to sum")
        print(" 3. Give a start value to find a root")
        print(" 4. Exit")

        choice = input("Enter choice(1/2/3/4): ")

        if choice == '1':
            n = int(input("Enter a number: "))
            bounce(n)  # assuming bounce is defined
        elif choice == '2':
            n = int(input("Enter a number: "))
            tvarsumman2(n)    # assuming sum2 is defined
        elif choice == '3':
            x0 = float(input("Enter a start value: "))
            print("Root:", solve(f1, x0, 1e-6))  # assuming f1 and solve are defined
        elif choice == '4':
            print("Exiting...")
            break  # exit the loop
        else:
            print("Invalid input")