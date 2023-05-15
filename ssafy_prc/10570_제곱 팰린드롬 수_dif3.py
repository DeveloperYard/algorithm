import math


def check_pal(s):
    s = list(s)
    length = len(s)//2

    for i in range(length):
        if s[i] != s[len(s)-i-1]:
            return False

    return True


def sol(a, b):
    ans = 0

    for i in range(a, b+1):
        comV1 = math.sqrt(i)
        comV2 = int(math.sqrt(i))
        if comV1 != comV2:
            continue
        if check_pal(str(i)) and check_pal(str(comV2)):
            ans += 1
    return ans


for tc in range(int(input())):
    a, b = map(int, input().split())
    res = sol(a, b)
    print(f'#{tc+1} {str(res)}')