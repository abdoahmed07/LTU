def kostnad(P,R,A):
    k = P + ((A + 1) * R * P) / 2
    print(f"The total cost of the loan is:  {k} kr")


kostnad(50000, 0.03, 10)