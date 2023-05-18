def sol(n):
    return True if n % 2 == 0 else  False


for tc in range(int(input())):
    res = "Even "if sol(int(input())) else "Odd"
    print(f'#{tc+1} {res}')