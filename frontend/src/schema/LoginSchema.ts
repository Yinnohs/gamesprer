import z from 'zod'

export const loginSchema = z.object({
  email: z
    .string()
    .email({
      message: 'Email have an incorrect format ',
    })
    .nonempty({
      message: 'Email should not be null',
    }),
  password: z
    .string()
    .min(8, {
      message: 'Password to short should have atleat 8 characters',
    })
    .max(24, {
      message: 'Password to long should have at moximun 24 characters',
    })
    .nonempty({
      message: 'password should not be empty',
    }),
})
