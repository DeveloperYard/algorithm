def solution(topping):
    answer = 0

    ch = dict()
    do = dict()

    for i in range(len(topping)):
        if topping[i] in ch:
            ch[topping[i]] += 1
        else:
            ch[topping[i]] = 1

    idx = len(topping)-1
    while 1:
        if topping[idx] not in do:  # 동생이 가지고 있는 토핑 리스트에 없는 경우
            do[topping[idx]] = 1  # 추가
            if topping[idx] in ch:  # 철수한테 있었다면 하나를 빼고, 0개라면 없애버림
                ch[topping[idx]] -= 1
                if ch[topping[idx]] == 0:
                    del ch[topping[idx]]
        else:
            do[topping[idx]] += 1
            if topping[idx] in ch:
                ch[topping[idx]] -= 1
                if ch[topping[idx]] == 0:
                    del ch[topping[idx]]

        idx -= 1
        if len(ch) == len(do):  # 토핑의 개수가 같다면 답에 하나 추가
            answer += 1
        elif len(ch) < len(do): # 동생의 토핑이 더 많을 경우 break
            break

    return answer  # 이렇게 하면 실행시간이 너무 길어짐!!! 다른 방법을 찾자.


topping = list(map(int, input().split()))
print(solution(topping))