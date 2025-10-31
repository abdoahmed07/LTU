def calc_recipe(P):
    B_f = (15 / 4) * P
    Bc_f = (3 / 4) * P
    E = int(3 / 4 * P)
    S = (3 / 4) * P
    V_s = (2 / 4) * P
    B_p = (2 / 4) * P
    F = (3 / 4.0) * P
    B = (75 / 4) * P
    W = (1 / 4) * P
    print(f"For {P} people, you will need:")
    print("For shape:")
    print(f"  {B_f} g butter")
    print(f"  {Bc_f} msk ströbröd")
    print("Sugar cake:")
    print(f"  {E} eggs")
    print(f"  {S} dl powdered sugar")
    print(f"  {V_s} tsp vanilla sugar")
    print(f"  {B_p} tsp baking powder")
    print(f"  {F} dl flour")
    print(f"  {B} g butter")
    print(f"  {W} dl water")


def calc_mix_time(P):
    mix_time = 10 + P 
    return mix_time


def calc_baking_time(P):
    bake_time = 30 + (P * 3)
    return bake_time


def print_recipe(P):
    calc_recipe(P)
    

    # Calculate times and assign to variables
    Mix_time = calc_mix_time(P)
    Baking_time = calc_baking_time(P)
    Total_time = Mix_time + Baking_time

  
    print(f"Mixing time: {Mix_time} minutes")
    print(f"Baking time: {Baking_time} minutes")
    print(f"Total time: {Total_time} minutes")



# Recipe for 4 people
print_recipe(4)

print()  # Blank line between recipes

# Recipe for 7 people
print_recipe(7)


